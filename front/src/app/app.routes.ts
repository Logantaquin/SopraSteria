import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PlanningComponent} from './planning/planning.component'
import { PlanningAdminComponent} from './planning-admin/planning-admin.component'
import { AddBureauComponent} from './add-bureau/add-bureau.component'
import { AddUserComponent} from './add-user/add-user.component'
import { UserComponent} from './user/user.component'
import { GenerateComponent } from './generate/generate.component'

export const routes: Routes = [
  {path: '',component: HomeComponent,},
  {path: 'planning', component: PlanningComponent },
  {path : 'add-bureau', component : AddBureauComponent},
  {path : 'add-user', component : AddUserComponent},
  {path : 'user' , component : UserComponent},
  {path : 'planning-admin', component: PlanningAdminComponent},
  {path : 'generate' , component : GenerateComponent},
];
