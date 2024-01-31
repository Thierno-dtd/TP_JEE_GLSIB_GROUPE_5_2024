import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListProduitsComponent } from './list-produits/list-produits.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeOneComponent } from './home-one/home-one.component';
import { HomeTwoComponent } from './home-two/home-two.component';
import { HomeComponent } from './home/home.component';
import { ProduitComponent } from './produit/produit.component';

const routes: Routes = [
  {path : "liste-produits", component : ListProduitsComponent},
  {path : "login", component : LoginComponent},
  {path : "signup", component : SignUpComponent},
  {path : "homeOne", component : HomeOneComponent},
  {path : "homeTwo", component : HomeTwoComponent},
  {path : "home", component : HomeComponent},
  {path : "produit", component :ProduitComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
