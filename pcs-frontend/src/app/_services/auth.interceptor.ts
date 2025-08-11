import { HttpHeaders, HttpInterceptorFn } from "@angular/common/http";

export const authInterceptor:HttpInterceptorFn=(request,next)=>{
    const token = localStorage.getItem('token')??'';
    // const headers = new HttpHeaders().set('Authorization', token ? `Bearer ${token}`:'');
    request = request.clone({
        // headers: headers
        setHeaders:{
            Authorization: token ? `Bearer ${token}`:'',
        },
    });
    return next(request);
}