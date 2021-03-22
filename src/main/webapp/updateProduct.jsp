<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: center; margin-top: 20px" >Edit Product</h1>
<div class="container" style="margin-top: 20px">
    <form method="post" style="padding-top: 50px" action="/productController?action=update">
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputName">Product Name</label>
                <input type="text" name="name" class="form-control" id="inputName" value="${product.getName()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputPrice">Product Price</label>
                <input type="text" name="price" class="form-control" id="inputPrice" value="${product.getPrice()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputQuantity">Product Quantity</label>
                <input type="text" name="quantity" class="form-control" id="inputQuantity" value="${product.getQuantity()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputColor">Product Color</label>
                <input type="text" name="color" class="form-control" id="inputColor" value="${product.getColor()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputColor">Product Description</label>
                <input type="text" name="des" class="form-control" id="inputDescription" value="${product.getDescription()}">
            </div>
        </div>
        <div class="form-group col-md-13">
            <label for="inputState">Category</label>
            <select id="inputState" class="form-control" name="name_category">
                <option>Phone</option>
                <option>Television</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Update</button>
        <a href="/productController">
            <button type="button" class="btn btn-secondary">Back</button>
        </a>
    </form>
</div>
</body>
</html>