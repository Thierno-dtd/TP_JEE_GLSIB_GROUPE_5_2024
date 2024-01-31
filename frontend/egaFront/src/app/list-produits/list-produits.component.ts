import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-produits',
  templateUrl: './list-produits.component.html',
  styleUrls: ['./list-produits.component.less'],
})
export class ListProduitsComponent implements OnInit {
   produits: Array<any> = []
    constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<Array<any>>("url")
    .subscribe({
      next : data =>{this.produits = data
    },
    error : err =>{
      console.log(err)
    }
  })
  /*handleCheckedProduit(produit : any){
    this.http.patch<any>(`url/${produit.id}`, body{})
    .subcribe({
      next : updateProduit =>{
        this.produit.map(p =>)
      }
    })
  }*/
  }
}
