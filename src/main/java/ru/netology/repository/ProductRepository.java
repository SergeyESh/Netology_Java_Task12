package ru.netology.repository;

import ru.netology.domain.AlreadyExistsException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

public class ProductRepository {

    private Product[] items = new Product[0];

    public void save(Product product) {
        if (findById(product.getId()) == product) {
            throw new AlreadyExistsException(
                    "This element whith id: " + product.getId() + " is in the repository"
            );
        }
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = product;
        items = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product product : items) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        items = tmp;
    }

    public Product findById(int id) {
        for (Product product : getItems()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product[] getItems() {
        return items;
    }
}
