export class Menu {}
export class Permission {
    [key: string]: any;
    path?: string;
    pPath?: string;
    id?: number;
    pid?: number;
    name: string;
    value: string;
    uri: string | string[];
    status?: number;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    type: number;

    tenantSide?: number;
    children?: Permission[];

    static get menus(): { [key: string]: Permission } {
        return {
            // 首页
            home: { path: 'home', pPath: null, name: '首页', uri: ['/', 'home'], value: null, type: 0 },
            // 基础信息

            basic: { path: 'basic', pPath: null, name: '基础信息', uri: ['/', 'b'], value: 'basic', type: 1 },

            user: {
                path: 'user',
                pPath: 'basic',
                name: '用户管理',
                uri: ['/', 'b', 'user'],
                value: 'MENU_USER',
                type: 1
            },
            role: {
                path: 'role',
                pPath: 'basic',
                name: '角色管理',
                uri: ['/', 'b', 'role'],
                value: 'MENU_ROLE',
                type: 1
            },
            create: {
                path: 's',
                pPath: null,
                name: '创建任务',
                uri: ['/', 'b'],
                value: '',
                type: 1
            },
            setup: {
                path: 's',
                pPath: 'create',
                name: '手动创建',
                uri: ['/', 'b', 's', 'setup'],
                value: '',
                type: 1
            },
            intake: {
                path: 's',
                pPath: 'create',
                name: '导入任务',
                uri: ['/', 'b', 's', 'intake'],
                value: '',
                type: 1
            },
            supervision: {
                path: 's', // s=> supervision
                pPath: null,
                name: '我督办的',
                uri: ['/', 'b'],
                value: '',
                type: 1
            },
            s_approve: {
                path: 's_approve',
                pPath: 'supervision',
                name: '待审批', // 等待审批
                uri: ['/', 'b', 's', 'supervisor', 'approve'],
                value: '',
                type: 1
            },
            s_todo: {
                path: 's_todo',
                pPath: 'supervision',
                name: '待分配',
                uri: ['/', 'b', 's', 'supervisor', 'todo'],
                value: '',
                type: 1
            },
            reject: {
                // FIXME: 为啥就你不能加 s_前缀
                path: 'reject',
                pPath: 'supervision',
                name: '驳回',
                uri: ['/', 'b', 's', 'supervisor', 'reject'],
                value: '',
                type: 1
            },
            initiator: {
                path: 'i',
                pPath: null,
                name: '我发起的',
                uri: ['/', 'b'],
                value: '',
                type: 1
            },
            i_approve: {
                path: 'i_approve',
                pPath: 'initiator',
                name: '待审批',
                uri: ['/', 'b', 's', 'initiator', 'approve'],
                value: '',
                type: 1
            },
            i_confirmed: {
                path: 'confirmed',
                pPath: 'initiator',
                name: '已审批',
                uri: ['/', 'b', 's', 'initiator', 'confirmed'],
                value: '',
                type: 1
            },
            i_delay: {
                path: 'delay',
                pPath: 'initiator',
                name: '延期申请',
                uri: ['/', 'b', 's', 'initiator', 'delay'],
                value: '',
                type: 1
            },
            viser: { path: 'viser', pPath: null, name: 'Viser', uri: ['/', 'b'], value: '', type: 1 },
            line: { path: 'line', pPath: 'viser', name: '线形图', uri: ['/', 'b'], value: '', type: 1 },
            oneLadder: {
                path: 'oneLadder',
                pPath: 'line',
                name: '基础折线图',
                uri: ['/', 'b', 'viser', 'oneLadder'],
                value: '',
                type: 1
            },
            // 我的任务
            my_task: {
                path: 'mytask',
                pPath: null,
                name: '我的任务',
                uri: ['/', 'b'],
                value: 'basic',
                type: 0
            },
            task_list: {
                path: 'tasklist',
                pPath: 'my_task',
                name: '任务列表',
                uri: ['/', 'b', 'mytask', 'tasklist'],
                value: 'basic',
                type: 1
            },
            ongoing_tasks: {
                path: 'ongoing_tasks',
                pPath: 'my_task',
                name: '正在进行',
                uri: ['/', 'b', 'mytask', 'ongoing'],
                value: 'basic',
                type: 1
            },
            redeploy: {
                path: 'redeploy',
                pPath: 'my_task',
                name: '任务转派',
                uri: ['/', 'b', 'mytask', 'redeploy'],
                value: 'basic',
                type: 1
            },
            oneFold: {
                path: 'oneFold',
                pPath: 'line',
                name: '多条折线图',
                uri: ['/', 'b', 'viser', 'oneFold'],
                value: '',
                type: 1
            },
            twoCurve: {
                path: 'twoCurve',
                pPath: 'line',
                name: '多条曲线',
                uri: ['/', 'b', 'viser', 'twoCurve'],
                value: '',
                type: 1
            },
            twoFold: {
                path: 'twoFold',
                pPath: 'line',
                name: '梯形折线图',
                uri: ['/', 'b', 'viser', 'twoFold'],
                value: '',
                type: 1
            },
            twoLadder: {
                path: 'twoLadder',
                pPath: 'line',
                name: '多条梯形折线图',
                uri: ['/', 'b', 'viser', 'twoLadder'],
                value: '',
                type: 1
            },
            bar: { path: 'bar', pPath: 'viser', name: '柱状图', uri: ['/', 'b'], value: '', type: 1 },
            simpleBar: {
                path: 'simpleBar',
                pPath: 'bar',
                name: '基础柱状图',
                uri: ['/', 'b', 'viser', 'simpleBar'],
                value: '',
                type: 1
            },
            doubleBar: {
                path: 'doubleBar',
                pPath: 'bar',
                name: '分组柱状图',
                uri: ['/', 'b', 'viser', 'doubleBar'],
                value: '',
                type: 1
            },
            stackBar: {
                path: 'stackBar',
                pPath: 'bar',
                name: '堆叠柱状图',
                uri: ['/', 'b', 'viser', 'stackBar'],
                value: '',
                type: 1
            },
            intervalBar: {
                path: 'intervalBar',
                pPath: 'bar',
                name: '区间柱状图',
                uri: ['/', 'b', 'viser', 'intervalBar'],
                value: '',
                type: 1
            },
            percentBar: {
                path: 'percentBar',
                pPath: 'bar',
                name: '百分比柱状图',
                uri: ['/', 'b', 'viser', 'percentBar'],
                value: '',
                type: 1
            },
            pie: { path: 'viser', pPath: 'viser', name: '饼图', uri: ['/', 'b'], value: '', type: 1 },
            basepie: {
                path: 'pie',
                pPath: 'pie',
                name: '基础饼图',
                uri: ['/', 'b', 'viser', 'pie'],
                value: '',
                type: 1
            },
            ring: {
                path: 'ring',
                pPath: 'pie',
                name: '环形饼图',
                uri: ['/', 'b', 'viser', 'ring'],
                value: '',
                type: 1
            },
            radar: { path: 'viser', pPath: 'viser', name: '雷达图', uri: ['/', 'b'], value: '', type: 1 },
            radarBlock: {
                path: 'radarBlock',
                pPath: 'radar',
                name: '雷达块图',
                uri: ['/', 'b', 'viser', 'radarBlock'],
                value: '',
                type: 1
            },
            radarLine: {
                path: 'radarLine',
                pPath: 'radar',
                name: '雷达线图',
                uri: ['/', 'b', 'viser', 'radarLine'],
                value: '',
                type: 1
            }
        };
    }

    static get menuTree(): Permission[] {
        const topMenus = [];
        const ms = this.menus;
        for (const k in ms) {
            if (ms[k].pPath !== null) {
                for (const pK in ms) {
                    if (pK === ms[k].pPath) {
                        ms[pK].children = ms[pK].children ? ms[pK].children : [];
                        ms[pK].children.push(ms[k]);
                    }
                }
            } else {
                topMenus.push(ms[k]);
            }
        }
        return topMenus;
    }
    static getPermissions() {
        return [{ key: 'Users', title: '用户管理', isLeaf: true }, { key: 'Roles', title: '角色管理', isLeaf: true }];
    }
    static getCheckedPermissions(permissionIds, ps = null) {
        ps = ps ? ps : [];
        for (const item of permissionIds) {
            if (item.checked) {
                ps.push(item.key);
            }
            if (item.children && item.children.length) {
                this.getCheckedPermissions(item.children, ps);
            }
        }
        return ps;
    }
}
