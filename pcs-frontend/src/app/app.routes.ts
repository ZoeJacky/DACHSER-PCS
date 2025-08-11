import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './login/login';
import { Admin } from './admin/admin';
import { User } from './user/user';
import { Forbidden } from './forbidden/forbidden';
import { CostAdmin } from './cost-admin/cost-admin';
import { PaymentAdmin } from './payment-admin/payment-admin';
import { FinanceDep } from './fin-dep/fin-dep';
import { Shipment } from './shipment/shipment';
import { AddIncome } from './add-income/add-income';
import { UpdateIncome } from './update-income/update-income';
import { AddCost } from './add-cost/add-cost';
import { UpdateCost } from './update-cost/update-cost';

export const routes: Routes = [
    { path:'home', component:Home },
    { path:'login', component:Login },
    { path:'admin', component:Admin },
    { path:'user', component:User },
    { path:'forbidden', component:Forbidden},
    { path: 'cost-admin', component:CostAdmin},
    { path: 'payment-admin', component:PaymentAdmin},
    { path: 'shipments', component:FinanceDep},
    // { path: 'add-shipment', component:Shipment},
    { path: 'add-income', component: AddIncome},
    { path: 'update-income/:id', component:UpdateIncome },
    { path: 'add-cost', component: AddCost},
    { path: 'update-cost/:id', component: UpdateCost},
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // Add default route
    { path: '**', redirectTo: '/home' } // Wildcard for unknown routes
];
