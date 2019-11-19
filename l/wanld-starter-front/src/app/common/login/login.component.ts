import { Component, Injector, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { ComponentBase } from 'src/app/_shared/components/component-base';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.less']
})
export class LoginComponent extends ComponentBase implements OnInit {
    image = 'http://localhost:8080/api/auth/captcha/';

    captchaUri: string;
    captchaId: string;
    captchaValidateStatus: any;
    loginForm: FormGroup;

    constructor(protected injector: Injector, protected router: Router) {
        super(injector);
        this.loginForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required),
            code: new FormControl('', [Validators.required]),
            rememberClient: new FormControl(false)
        });
        this.captchaUri = this.authService.captchaUri(null);
        this.loginForm
            .get('code')
            .valueChanges.pipe(debounceTime(1000))
            .subscribe(res => {
                if (this.loginForm.dirty) {
                    this.authService
                        .checkInputCaptcha(res, this.captchaId)
                        .pipe(debounceTime(800))
                        .subscribe(v => {
                            this.captchaValidateStatus = v ? 'success' : 'error';
                        });
                }
            });
    }

    ngOnInit() {
        this.getCaptchaId();
    }

    getCaptchaId() {
        this.authService.getCaptchaId().subscribe(
            resp => {
                this.captchaId = resp;
                this.captchaUri = this.authService.captchaUri(resp);
                this.loginForm.get('code').setValue('');
            },
            () => {
                this.captchaUri = this.authService.captchaUri(null);
            }
        );
    }

    login() {
        if (this.loginForm.invalid || this.captchaValidateStatus !== 'success') {
            return;
        }

        this.loginForm.value['captchaId'] = this.captchaId;
        this.authService.login(this.loginForm.value).subscribe(
            res => {
                const url = this.activatedRoute.snapshot.queryParams['url'];
                if (url) {
                    this.router.navigateByUrl(url);
                } else {
                    this.router.navigate(['/']);
                }
            },
            () =>
                this.nzmodalService.error({
                    nzTitle: '登陆失败',
                    nzContent: '请检查登录信息，重新输入'
                    // nzTitle: error.message ? error.message : '登陆失败',
                    // nzContent: error.details
                })
        );
    }
}
