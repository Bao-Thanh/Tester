export class Product {
    id: number;
    name: string;
    imageUrls: string;
    price: number;

    constructor(id: number,
        name: string,
        imageUrls: string,
        price: number,) {
        this.id=id;
        this.name=name;
        this.imageUrls=imageUrls;
        this.price=price;
    }
}