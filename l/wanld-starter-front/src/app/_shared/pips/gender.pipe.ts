import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'gender'
})
export class GenderPipe implements PipeTransform {
    transform(value: number | string | boolean, exponent: string): string {

        if (value === '男' || value === '女') {
            return value;
        }

        if (typeof value === 'string') {
            value = parseInt(value, 10);
        }

        if (typeof value === 'boolean') {
            return value ? '男' : '女';
        }

        switch (value) {
            case 1:
                return '男';
            case 2:
                return '女';
            default:
                return '';
        }
    }
}