import { ValidatorFn, AbstractControl } from '@angular/forms';

export class AppValidators {

    /**
     * 为null时返回 required;
     * 包含非汉字时返回 chinese;
     */
    static chinese(control: any): { [key: string]: any } {
        if (!control.value || !control.value.trim()) {
            return { required: '不能为空！' };
        }
        if (!/^[\u4e00-\u9fa5]{0,}$/.test(control.value)) {
            return {
                chinese: '只能输入汉字！'
            };
        }
    }

    /**
     * 为null时返回 required;
     * 不符合（中文、字母、数字、下划线）返回 nickname;
     */
    static nickName(control: any): { [key: string]: any } {
        if (!control.value || !control.value.trim()) {
            return { required: '不能为空！' };
        }
        if (!/^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(control.value)) {
            return {
                nickname: '只能包含中文、字母、数字、下划线！'
            };
        }
    }

    /**
     * 为null时返回 required;
     * 不符合（字母、数字、下划线）返回 userName;
     */
    static username(control: any): { [key: string]: any } {
        if (!control.value || !control.value.trim()) {
            return { required: '不能为空！' };
        }
        if (!/^\w+$/.test(control.value)) {
            return {
                userName: '只能包含字母、数字、下划线！'
            };
        }
    }



    /**
     * 终极版验证
     * @param opt 自定义条件
     */
    static optional(opt: ValidOptional): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {

            if (opt) {
                if (opt.required) {
                    if (!control.value || !control.value.trim()) {
                        return { error: '不能为空！' };
                    }
                }
                if (opt.noWhitespace) {
                    if (control.value.indexOf(' ') >= 0) {
                        return {
                            error: '不能包含空格！'
                        };
                    }
                }

                if (opt.email) {
                    if (!/^([A-Za-z0-9_\-\.\u4e00-\u9fa5])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,8})$/.test(control.value)) {
                        return { error: '邮箱格式不正确' };
                    }
                }


                if (opt.maxLength) {
                    if (control.value.length > opt.maxLength) {
                        return { error: `输入最大长度不能超过${opt.maxLength}字符！` };
                    }
                }
                if (opt.zhEnN_) {
                    if (!/^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(control.value)) {
                        return {
                            error: '只能输入中文、字母、数字、下划线！'
                        };
                    }
                }
                if (opt.en_) {
                    if (!/^[A-Za-z_]+$/.test(control.value)) {
                        return {
                            error: '只能输入字母和下划线!'
                        };
                    }
                }
                if (opt.en_captical) {
                    if (!/^[A-Z_]+$/.test(control.value)) {
                        return {
                            error: '只能输入大写字母和下划线!'
                        };
                    }
                }


            }
            return null;
        };
    }



}
class ValidOptional {
    required?: boolean;
    maxLength?: number;
    minLength?: number;
    zhEnN_?: boolean;
    en_?: boolean;
    en_captical?: boolean;

    noWhitespace?: boolean;
    email?: boolean;

}
