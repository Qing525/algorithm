import { Component, OnInit, Injector, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

import { NzTreeNodeOptions, NzContextMenuService, NzDropdownMenuComponent } from 'ng-zorro-antd';

import { ComponentBase } from 'src/app/_shared/components/component-base';

import { SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-task-form',
    templateUrl: './task-form.component.html',
    styleUrls: ['./task-form.component.less']
})
export class TaskFormComponent extends ComponentBase implements OnInit {
    @Output() formPayload = new EventEmitter();
    @Input() isDispatchForm: boolean;
    rootNodeTitle: string;
    childNodeTitle: string;
    nodePid: string;
    isAppendChildNode = false;
    isEditChildNode = false;
    isVisible = false;
    isLoading = false;
    taskForm: FormGroup;
    treeNode: FormGroup;
    contacts = [];
    types: NzTreeNodeOptions[] = [];
    private supervisionService: SupervisionService;
    constructor(
        private fb: FormBuilder,
        protected injector: Injector,
        private nzContextMenuService: NzContextMenuService
    ) {
        super(injector);
        this.supervisionService = this.injector.get(SupervisionService);
        this.taskForm = this.fb.group({
            initiatorUserId: ['', [Validators.required]], // 仅仅是展示联系人,
            title: ['', [Validators.required]],
            performance: ['', [Validators.required]],
            type: ['', [Validators.required]],
            level: ['', [Validators.required]], // 默认 2 ( 子任务 ) 问题是这里要是创建任务就会通过啊
            supervisionCategoryId: ['', [Validators.required]],
            initiateTime: [, []], // 二级任务不需要这个
            endTime: [, [Validators.required]],
            content: ['', [Validators.required]],
            remark: ['']
        });
    }
    ngOnInit() {
        this.getCategory();
    }
    getCategory() {
        this.supervisionService.getCategory().subscribe(res => (this.types = res));
    }
    submitTask(initiatorUserId, level) {
        // 创建任务手选  拆分任务 这个字段从路由解析过来的
        if (level === '2') {
            // 如果是子级任务
            this.taskForm.patchValue({ initiatorUserId });
            this.taskForm.patchValue({ level });
        }
        if (this.taskForm.valid) {
            this.formPayload.emit(this.taskForm.value);
        } else {
            // tslint:disable-next-line: forin
            for (const i in this.taskForm.controls) {
                this.taskForm.controls[i].markAsDirty();
                this.taskForm.controls[i].updateValueAndValidity();
            }
        }
    }
    searchContact() {
        this.isLoading = true;
        this.supervisionService.getAllUser().subscribe(res => {
            this.isLoading = false;
            this.contacts = res;
        });
    }

    contextMenu($event: MouseEvent, menu: NzDropdownMenuComponent): void {
        this.nzContextMenuService.create($event, menu);
    }
    deleteNode(node) {
        this.supervisionService.delCategory(node.key).subscribe(res => {
            this.getCategory();
            this.nzMessageService.create('success', `成功删除分类: ${node.title}`);
        });
    }
    addRootNode() {
        this.supervisionService.addCategory({ pid: '0', title: this.rootNodeTitle }).subscribe(res => {
            this.getCategory();
            this.nzMessageService.create('success', `成功添加分类: ${this.rootNodeTitle}`);
        });
        this.rootNodeTitle = '';
    }
    // 以下代码需要优化
    addChildNode(node) {
        this.isAppendChildNode = true; // 控制输入框
        this.nodePid = node.key;
    }
    editNode(node) {
        this.childNodeTitle = node.title;
        this.isEditChildNode = true; // 控制输入框
        this.nodePid = node.key;
    }
    confirmAddNode() {
        this.isAppendChildNode = false;
        this.supervisionService.addCategory({ pid: this.nodePid, title: this.childNodeTitle }).subscribe(res => {
            this.getCategory();
            this.nzMessageService.create('success', `成功添加分类: ${this.childNodeTitle}`);
        });
        this.childNodeTitle = '';
    }
    confirmEditNode() {
        this.isEditChildNode = false;
        this.supervisionService.upCategory({ id: this.nodePid, title: this.childNodeTitle }).subscribe(res => {
            this.getCategory();
            this.nzMessageService.create('success', `成功修改分类: ${this.childNodeTitle}`);
        });
        this.childNodeTitle = '';
    }
}
