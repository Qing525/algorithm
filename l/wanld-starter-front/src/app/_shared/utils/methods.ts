// get array of object uniq by key; return uniq key array
// Array.from(new Set(array.map(item => item.key)))

/**
 * 动态排序：array.sort(dyanmicSort('property'))
 * @param property 对象的属性名
 */
export function dynamicSort(property) {
    let sortOrder = 1;
    if (property[0] === '-') {
        sortOrder = -1;
        property = property.substr(1);
    }
    return (a, b) => {
        const result = a[property] < b[property] ? -1 : a[property] > b[property] ? 1 : 0;
        return result * sortOrder;
    };
}

export function uniq(a) {
    const prims = {
            boolean: {},
            number: {},
            string: {}
        },
        objs = [];

    return a.filter(item => {
        const type = typeof item;
        if (type in prims) {
            return prims[type].hasOwnProperty(item) ? false : (prims[type][item] = true);
        } else {
            return objs.indexOf(item) >= 0 ? false : objs.push(item);
        }
    });
}

/** uniq a array by a value */
export function uniqBy(a, key): any[] {
    const seen = {};
    return a.filter(function(item) {
        const k = key(item);
        return seen.hasOwnProperty(k) ? false : (seen[k] = true);
    });
}

export function uniqES6(a: any) {
    return Array.from(new Set(a));
}

export function uniqByES6(a, key) {
    const seen = new Set();
    return a.filter(item => {
        const k = key(item);
        return seen.has(k) ? false : seen.add(k);
    });
}

export function uniqSort(a) {
    return a.sort().filter(function(item, pos, ary) {
        return !pos || item !== ary[pos - 1];
    });
}

/** 表单验证，旧密码与新密码必须一样
 * 不一致返回：{mismatchedPasswords: true}
 */
export function matchingPasswords(passwordKey: string, confirmPasswordKey: string) {
    return (group: any): { [key: string]: any } => {
        const password = group.controls[passwordKey];
        const confirmPassword = group.controls[confirmPasswordKey];

        if (password.value !== confirmPassword.value) {
            return {
                mismatchedPasswords: true
            };
        }
    };
}

export function whitespaceValidator() {
    return (control: any): { [key: string]: any } => {
        console.log(control.value);
        if (control.value.indexOf(' ') >= 0) {
            return {
                whitespave: true
            };
        }
    };
}
