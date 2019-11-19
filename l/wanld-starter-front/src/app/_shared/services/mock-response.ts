export class MockResponse {

    code: number;
    data: object;
    message: string;
    success: boolean;

    constructor(_code, _data, _message, _success) {
        this.code = _code;
        this.data = _data;
        this.message = _message;
        this.success = _success;
    }

}
