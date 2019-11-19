export function getTime() {
    const now = new Date();
    let hour, minutes;
    const h = now.getHours();
    const m = now.getMinutes();
    h < 10 ? (hour = '0' + h) : (hour = h);
    m < 10 ? (minutes = '0' + m) : (minutes = m);
    return `${hour}:${minutes}`;
}
/**
 * @export
 * @param {number} type 0=>20190101 1=>2019-01-01
 */
export function Timestramp(type: number): string {
    let month;
    const now = new Date();
    const year = now.getFullYear();
    const m = now.getMonth() + 1;
    const date = now.getDate();
    m < 10 ? (month = '0' + m) : (month = m);
    if (type === 0) {
        return `${year}${month}${date}`;
    } else {
        return `${year}-${month}-${date}`;
    }
}
