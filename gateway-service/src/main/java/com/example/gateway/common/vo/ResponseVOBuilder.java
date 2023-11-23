package com.example.gateway.common.vo;

public class ResponseVOBuilder<T> {
    private final ResponseVO<T> responseVO = new ResponseVO<>();

    private ResponseVOBuilder<T> result(String result) {
        responseVO.setResult(result);
        return this;
    }

    private ResponseVOBuilder<T> status(String status) {
        responseVO.setStatus(status);
        return this;
    }

    public ResponseVOBuilder<T> success() {
        return new ResponseVOBuilder<T>().result("Succeed").status("200");
    }

    public ResponseVOBuilder<T> fail() {
        return new ResponseVOBuilder<T>().result("Failed");
    }

//    public ResponseVOBuilder<T> error(ErrorVo error) {
//        responseVO.setError(error);
//        return this;
//    }

    public ResponseVOBuilder<T> data(final T body) {
        responseVO.setData(body);
        return this;
    }

    public ResponseVO<T> build() {
        return responseVO;
    }
}
