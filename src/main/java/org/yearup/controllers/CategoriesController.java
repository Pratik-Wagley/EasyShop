package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

// add the annotations to make this a REST controller
@RestController
// add the annotation to make this controller the endpoint for the following url
@RequestMapping ("/categories")

    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired  CategoriesController(ProductDao productDao, CategoryDao categoryDoa)
    {
        this.productDao = productDao;
        this.categoryDao = categoryDoa;
    }


    // add the appropriate annotation for a get action
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAll() {        // find and return all categories
        return categoryDao.getAllCategories();
    }


    // add the appropriate annotation for a get action

    @GetMapping(path = "/{id}")
    public Category getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id);
        }
        return category;
    }


    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products

    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        return productDao.listByCategoryId(categoryId);
    }


    // add annotation to call this method for a POST action
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    // add annotation to ensure that only an ADMIN can call this function
    @Secured("ROLE_ADMIN")
    public Category addCategory(@RequestBody Category category)
    {
        // insert the category
        return categoryDao.create(category);

    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    // add annotation to ensure that only an ADMIN can call this function
    @Secured("ROLE_ADMIN")
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id, category);
        // update the category by id
    }




    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    // add annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
        // delete the category by id
    }

}
