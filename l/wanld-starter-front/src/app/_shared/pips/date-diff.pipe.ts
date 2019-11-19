import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'dateDiff'
})
export class DateDiffPipe implements PipeTransform {

    minute = 60;
    hour = this.minute * 60;
    day = this.hour * 24;
    month = this.day * 30;
    halfamonth = this.day * 15;
    transform(value: any, exponent: string): string {

        if (!value) {
            return '';
        }
        if (typeof value === 'string') {
            value = new Date(value.replace(/T/g, ' '));
        }
        const diff = (Date.now() - value.getTime()) / 1000;

        if (diff < this.minute) {
            return Math.floor(diff) + '秒前';
        } else if (diff < this.hour) {
            return Math.floor(diff / this.minute) + '分前';
        } else if (diff < this.day) {
            return Math.floor(diff / this.hour) + '小时前';
        } else if (diff < this.month) {
            return Math.floor(diff / this.day) + '天前';
        } else if (diff < this.month) {
            return Math.floor(diff / this.month) + '月前';
        }

    }
}
