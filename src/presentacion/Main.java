package presentacion;

import dominio.*;
import exception.*;

import java.util.*;

public class Main {
    public static Scanner in = new Scanner(System.in);
    static List<Empleado> empleadosDiurnos = new ArrayList<>();
    static List<Empleado> empleadosNocturnos = new ArrayList<>();
    static List<Producto> productosPerecederos = new ArrayList<>();
    static List<Producto> productosNoPerecederos = new ArrayList<>();
    static List<Oferta> ofertasList = new ArrayList<>();
    static List<Producto> basket = new ArrayList<>();
    static Empleado emp = new EmpleadoDiurno();
    static Producto prod = new ProductoPerecedero();
    static Oferta offer = new Oferta();
    static double totalPrice = 0;
    static double actualProd = 0;

    public static void main(String[] args) {
        empleadosDiurnos = emp.leerEmpleados(); //leemos los datos de los ficheros y gaurdamos todos los datos en listas
        emp = new EmpleadoNocturno();
        empleadosNocturnos = emp.leerEmpleados();

        productosPerecederos = prod.leerProductos();

        prod = new ProductoNoPerecedero();
        productosNoPerecederos = prod.leerProductos();

        ofertasList = offer.leerOfertas();

        loopMethod();
    }

    private static int loopMethod() {
        //noinspection InfiniteLoopStatement
        do {
            emp = login(emp, empleadosDiurnos, empleadosNocturnos); //metodo de verfificacion de datos

            System.out.println("Current loggedIn employee is: " + emp.getNombreUsuario());

            mainMenu();
        } while (true);
    }

    public static int mainMenu() { //menu principal
        System.out.println("Main Menu");
        System.out.println("[1] - Make order");
        System.out.println("[2] - Modify product");
        System.out.println("[3] - Change employee password");
        System.out.println("[4] - Show current productivity");
        System.out.println("[5] - Log out");
        System.out.println("\nEnter a number: ");

        try {
            switch (in.nextInt()) {
                case 1:
                    int sub = makeOrderMenu();
                    System.out.println(sub);
                    return 1;
                case 2:
                    prod = elegirProducto(productosPerecederos, productosNoPerecederos); //elegimos producto
                    System.out.println("You have chosen a product with id - " + prod.getCodigo() + " and which is have name -" + " " + prod.getNombre());
                    int sub2 = modifyProductMenu(); //escribimos menu de modificar
                    System.out.println(sub2);
                    return mainMenu();
                case 3:
                    passChange(emp, empleadosDiurnos, empleadosNocturnos); //cambiar contraseña
                    System.out.println("Password have changed successfully");
                    return mainMenu();
                case 4://mostrar productividad
                    System.out.println("Current productivity of " + emp.getNombreUsuario() + " is: " + actualProd);
                    return mainMenu();
                case 5:
                    System.out.println("Logged out successfully"); //log out
                    System.out.println("\nThank you for using our software - " + emp.getNombreUsuario());
                    System.out.println("--------------SESSION WAS TERMINATED SUCCESSFULLY--------------");
                    actualProd = 0;
                    return loopMethod();
                default:
                    System.err.println("Wrong menu number selected, enter correct number.");
                    return mainMenu();
            }
        } catch (InputMismatchException e1) {
            in.nextLine();
            System.err.println("Error, wrong data type");
        }
        return mainMenu();
    }

    public static int makeOrderMenu() {
        System.out.println("Making order menu: ");
        System.out.println("[1] - Add product");
        System.out.println("[2] - Visualise total price");
        System.out.println("[3] - Print facture");
        System.out.println("[4] - Show products in the basket");
        System.out.println("[5] - Close order");
        System.out.println("\nEnter a number: ");

        try {
            switch (in.nextInt()) {
                case 1:
                    in.nextLine();
                    basketAdd(productosPerecederos, productosNoPerecederos, basket, prod); //añadir producto a la cesta
                    System.out.println("You successfully have added products to your basket, there is no way back.");
                    System.out.println("Your basket has the next products:");
                    basketView(); //mostramos lo que tenemos en la cesta de momento
                    return makeOrderMenu();
                case 2:
                    System.out.printf("Total price is - %.2f$\n", totalPrice); //mostramos precio
                    return makeOrderMenu();
                case 3:
                    checkVisual(basket, emp, totalPrice); //factura
                    return makeOrderMenu();
                case 4:
                    System.out.println("Your basket has the next products:"); //
                    basketView(); //mostrar todos productos en la cesta (otra vez?)
                    return makeOrderMenu();
                case 5:
                    System.out.println("Close order");
                    actualProd = actualProdCounter(emp, totalPrice);
                    basket.clear();
                    totalPrice = 0;
                    return mainMenu();
                default:
                    System.out.println("Wrong menu number selected, enter correct number.");
                    return makeOrderMenu();
            }
        } catch (
                InputMismatchException e) {
            in.nextLine();
            System.err.println("Error, wrong data type");
        } catch (
                CloneNotSupportedException e1) {
            e1.printStackTrace();
        }
        return makeOrderMenu();

    }

    public static int modifyProductMenu() {
        System.out.println("Modify product menu: ");
        System.out.println("[1] - Change name");
        System.out.println("[2] - Change price");
        System.out.println("[3] - Change id");
        System.out.println("[4] - Add new products");
        System.out.println("[5] - Return to the main menu");
        System.out.println("\nEnter a number: ");

        try {
            switch (in.nextInt()) { //aqui todos metodos para modificar producto
                case 1:
                    in.nextLine();
                    productNameChanger(productosPerecederos, productosNoPerecederos, prod);
                    System.out.println("Name of the product have changed successfully to - " + prod.getNombre());
                    return mainMenu();
                case 2:
                    productPriceChanger(productosPerecederos, productosNoPerecederos, prod);
                    System.out.println("Price of the product have changed successfully to - " + prod.getPrecio() + "$");
                    return mainMenu();
                case 3:
                    productCodigoChanger(productosPerecederos, productosNoPerecederos, prod);
                    System.out.println("Id of the product have changed successfully to - " + prod.getCodigo());
                    return mainMenu();
                case 4:
                    productCountChanger(productosPerecederos, productosNoPerecederos, prod);
                    System.out.println("Number of the products were changed successfully to - " + prod.getUnidades());
                    return mainMenu();
                case 5:
                    System.out.println("return to main menu");
                    return mainMenu();
                default:
                    System.out.println("Wrong menu number selected, enter correct number.");
                    return modifyProductMenu();
            }
        } catch (InputMismatchException e1) {
            in.nextLine();
            System.err.println("Error, wrong data type");
        }
        return modifyProductMenu();
    }

    public static Empleado login(Empleado emp, List<Empleado> empleadosDiurnos, List<Empleado> empleadosNocturnos) {
        boolean usuarioB = false, passwordB = false;
        do {
            int usuario = 0;
            usuario = usernameVerification(in, usuario);
            System.out.println("Password:");
            String password = in.next();
            for (int i = 0; i < empleadosDiurnos.size() + empleadosNocturnos.size(); i++) { //Recorremos dos listas con datos de usuarios y comprobamos si
                // estan bien introducido los datos
                if (i < empleadosDiurnos.size()) {
                    if (empleadosDiurnos.get(i).getCodigoAcceso() == usuario) {
                        usuarioB = true;
                        if (empleadosDiurnos.get(i).getPassword().equals(password)) {
                            passwordB = true;
                            emp = empleadosDiurnos.get(i);
                        }
                    }
                } else {
                    if (empleadosNocturnos.get(i - empleadosDiurnos.size()).getCodigoAcceso() == usuario) {
                        usuarioB = true;
                        if (empleadosNocturnos.get(i - empleadosDiurnos.size()).getPassword().equals(password)) {
                            passwordB = true;
                            emp = empleadosNocturnos.get(i - empleadosDiurnos.size());
                        }
                    }
                }
            }
            try {
                if (!usuarioB) throw new ExceptionUsuario(); //en caso de que haya sido introducido mal contraseña o usuario, se lanza un error
                else if (!passwordB) throw new ExceptionPassword();
            } catch (ExceptionUsuario e1) {
                System.err.println("Usuario incorrecto");
            } catch (ExceptionPassword e2) {
                System.err.println("Password incorrecta");
            }

        } while (!usuarioB || !passwordB);
        return emp;
    }

    private static int usernameVerification(Scanner sc, int usuario) {  //metodo para comprobar nombre de usuario que debe ser numerico
        try {
            System.out.println("Username:");
            usuario = sc.nextInt();
        } catch (InputMismatchException e1) {
            sc.nextLine();
            System.err.println("Username is numeric");
            return usernameVerification(sc, usuario);
        }
        return usuario;
    }

    public static void passChange(Empleado emp, List<Empleado> empleadosNocturnos, List<Empleado> empleadosDiurnos) { //introducimos nueva contraseña
        System.out.print("Enter new user password: ");
        String newPass = in.next();
        for (int i = 0; i < empleadosDiurnos.size() + empleadosNocturnos.size(); i++) { //cambiamos la contraseña para el usuario
            if (i < empleadosDiurnos.size()) {
                if (emp.getPassword().equals(empleadosDiurnos.get(i).getPassword()) && (emp.getCodigoAcceso() == empleadosDiurnos.get(i).getCodigoAcceso())) {
                    empleadosDiurnos.get(i).setPassword(newPass);
                    emp.escribirEmpleados(empleadosDiurnos);
                }
            } else {
                if (emp.getPassword().equals(empleadosNocturnos.get(i - empleadosDiurnos.size()).getPassword()) && (emp.getCodigoAcceso() == empleadosNocturnos.get(i - empleadosDiurnos.size()).getCodigoAcceso())) {
                    empleadosNocturnos.get(i - empleadosDiurnos.size()).setPassword(newPass);
                    emp.escribirEmpleados(empleadosNocturnos);
                }
            }
        }
    }

    private static Producto elegirProducto(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos) {
        for (int i = 0; i < productosPerecederos.size(); i++) { //elegimos el producto existente para modificar
            System.out.println("Product number " + (i + 1) + " " + productosPerecederos.get(i)); //listamos todos los productos en almacen
        }
        for (int i = 0; i < productosNoPerecederos.size(); i++) {
            System.out.println("Product number " + (i + productosPerecederos.size() + 1) + " " + productosNoPerecederos.get(i));
        }
        System.out.print("\nEnter an id of product which you want modify: ");
        int modProduct;
        try {
            modProduct = in.nextInt();
        } catch (InputMismatchException e1) { //verificacion de que sea el numerico codigo de producto
            in.nextLine();
            System.err.println("Error, product id is numeric, enter the number:");
            return elegirProducto(productosPerecederos, productosNoPerecederos); //aqui te salta bucle infinito, porque te pide entrar BIEN el codigo de
            // producto
        }

        for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) {
            if (i < productosPerecederos.size()) {
                if (productosPerecederos.get(i).getCodigo() == modProduct) {
                    return productosPerecederos.get(i);
                }
            } else if (i >= productosPerecederos.size()) {
                if (productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo() == modProduct) {
                    return productosNoPerecederos.get(i - productosPerecederos.size());
                }
            }
        }
        System.err.println("Product does not exist, enter well an id of product:"); //si no hay producto en stock, llamamos otra vez ese metodo
        return elegirProducto(productosPerecederos, productosNoPerecederos);
    }

    private static void productNameChanger(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos, Producto prod) {
        // Metodo
        // para cambiar nombre de producto
        String newName = null;
        boolean exist = true;
        while (exist) {                 // comprobamos que no haya 2 nombres iguales
            System.out.print("\nEnter new name of product: ");
            newName = in.nextLine();
            while (newName == null || newName.trim().length() == 0) { // comprobamos que no sea nombre nulo o vacio
                System.err.println("You can't enter empty name,\n" +
                        "Enter new name of product: ");
                newName = in.nextLine();
            }
            for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //buscamos el producto con nombre igual
                if (i < productosPerecederos.size()) {
                    if (productosPerecederos.get(i).getNombre().equalsIgnoreCase(newName)) {
                        exist = true;
                        System.out.print("\nProduct with this name is already exist.");
                        break;
                    }
                } else {
                    if (productosNoPerecederos.get(i - productosPerecederos.size()).getNombre().equalsIgnoreCase(newName)) {
                        exist = true;
                        System.out.print("\nProduct with this name is already exist.");
                        break;
                    }
                }
                exist = false;
            }
        }
        for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //si nombre esta bien escrito, cambiamos el nombre
            if (i < productosPerecederos.size()) {
                if (prod.getCodigo() == productosPerecederos.get(i).getCodigo()) {
                    productosPerecederos.get(i).setNombre(newName);
                    prod.escribirProductos(productosPerecederos);
                }
            } else {
                if (prod.getCodigo() == productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo()) {
                    productosNoPerecederos.get(i - productosPerecederos.size()).setNombre(newName);
                    prod.escribirProductos(productosNoPerecederos);
                }
            }
        }
    }

    private static void productPriceChanger(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos, Producto prod) { // Metodo
        System.out.print("Introduzca nuevo precio para el producto: ");
        double newPrice = 0;
        try {
            newPrice = in.nextDouble(); //precio debe ser numerico
        } catch (InputMismatchException e1) {
            in.nextLine();
            System.err.println("Error, wrong data type");
        }
        while (newPrice <= 0) { //verificamos que sea un numero positivo
            System.out.println("No puedes tener precio negativo o nulo,\n" +
                    "Introduzca nuevo precio para el producto: ");
            newPrice = in.nextDouble();
        }
        for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //cambiamos el precio de producto
            if (i < productosPerecederos.size()) {
                if (prod.getCodigo() == productosPerecederos.get(i).getCodigo()) {
                    productosPerecederos.get(i).setPrecio(newPrice);
                    prod.escribirProductos(productosPerecederos);
                }
            } else {
                if (prod.getCodigo() == productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo()) {
                    productosNoPerecederos.get(i - productosPerecederos.size()).setPrecio(newPrice);
                    prod.escribirProductos(productosNoPerecederos);
                }
            }
        }
    }

    private static void productCodigoChanger(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos, Producto prod) {
        int newCode = 0;
        boolean exist = true;
        while (exist) {             // Comprobamos que no sean 2 codigos iguales o codigos negativos
            System.out.print("\nEnter new ID of product: ");
            try {
                newCode = in.nextInt();
            } catch (InputMismatchException e1) { //codigo debe ser numerico
                in.nextLine();
                System.err.println("Error, wrong data type");
                productCodigoChanger(productosPerecederos, productosNoPerecederos, prod);
            }
            while (newCode < 0) {
                System.err.println("You can't have ID with negative or null number,\n" +
                        "Enter new ID of product: ");
                newCode = in.nextInt();
            }
            for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //no puede ser 2 codigos iguales
                if (i < productosPerecederos.size()) {
                    if (productosPerecederos.get(i).getCodigo() == (newCode)) {
                        exist = true;
                        System.out.print("\nProduct with this ID is already exist.");
                        break;
                    }
                } else {
                    if (productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo() == (newCode)) {
                        exist = true;
                        System.out.print("\nProduct with this ID is already exist.");
                        break;
                    }
                }
                exist = false;
            }
        }
        for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //cambiamos el codigo
            if (i < productosPerecederos.size()) {
                if (prod.getCodigo() == productosPerecederos.get(i).getCodigo()) {
                    productosPerecederos.get(i).setCodigo(newCode);
                    prod.escribirProductos(productosPerecederos);
                }
            } else {
                if (prod.getCodigo() == productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo()) {
                    productosNoPerecederos.get(i - productosPerecederos.size()).setCodigo(newCode);
                    prod.escribirProductos(productosNoPerecederos);
                }
            }
        }
    }

    private static void productCountChanger(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos, Producto prod) {
        System.out.print("Enter amount of products you want yo add to store: ");
        int amountToAdd = 0;
        try {
            amountToAdd = in.nextInt(); //introducimos un numero entero para añadir
        } catch (InputMismatchException e1) {
            in.nextLine();
            System.err.println("Error, wrong data type");
        }
        while (amountToAdd <= 0) { //no puede ser un numero negativo o cero
            System.err.println("Number of products you want to add might be positive number,\n" +
                    "Enter the number another time: ");
            amountToAdd = in.nextInt();
        }
        for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) { //añadimos nueva cantidad a la existente
            if (i < productosPerecederos.size()) {
                if (prod.getCodigo() == productosPerecederos.get(i).getCodigo()) {
                    productosPerecederos.get(i).setUnidades(productosPerecederos.get(i).getUnidades() + amountToAdd);
                    prod.escribirProductos(productosPerecederos);
                }
            } else {
                if (prod.getCodigo() == productosNoPerecederos.get(i - productosPerecederos.size()).getCodigo()) {
                    productosNoPerecederos.get(i - productosPerecederos.size()).setUnidades(productosNoPerecederos.get(i - productosPerecederos.size()).getUnidades() + amountToAdd);
                    prod.escribirProductos(productosNoPerecederos);
                }
            }
        }
    }

    private static void basketAdd(List<Producto> productosPerecederos, List<Producto> productosNoPerecederos, List<Producto> basket, Producto prod) throws CloneNotSupportedException {
        for (int i = 0; i < productosPerecederos.size(); i++) { //elegimos el producto existente para modificar
            System.out.println("Product number " + (i + 1) + " " + productosPerecederos.get(i)); //listamos todos los productos en almacen
        }
        for (int i = 0; i < productosNoPerecederos.size(); i++) {
            System.out.println("Product number " + (i + productosPerecederos.size() + 1) + " " + productosNoPerecederos.get(i));
        }
        List<Producto> tempBasket = new ArrayList<>(); //creamos cesta temporal
        try {
            if (basket.size() == 16) {
                throw new ExceptionBasketLimit();
            }
        } catch (ExceptionBasketLimit e1) {
            System.err.println("You reached the limit of products you can add to basket."); //si en cesta hay 16 productos, salta excepcion
        }
        String prodName;
        int amountOfProducts = 0;
        boolean exist = false;
        while (!exist) {                       // Comprobamos que producto existe en almacen
            System.out.print("\nEnter the name of product you want to buy: ");
            prodName = in.nextLine();
            for (int i = 0; i < productosPerecederos.size() + productosNoPerecederos.size(); i++) {
                if (i < productosPerecederos.size()) {
                    if (productosPerecederos.get(i).getNombre().equalsIgnoreCase(prodName)) {
                        prod = productosPerecederos.get(i);
                        exist = true;
                        break;
                    }
                } else {
                    if (productosNoPerecederos.get(i - productosPerecederos.size()).getNombre().equalsIgnoreCase(prodName)) {
                        prod = productosNoPerecederos.get(i - productosPerecederos.size());
                        exist = true;
                        break;
                    }
                }
                exist = false;
            }
            if (!exist) {
                System.err.println("There is no product in stock."); //si no hay productos, empieza de nuevo el bucle
                makeOrderMenu();
            }
            System.out.println("There is " + prod.getUnidades() + " products on storage."); //escribimos cuantos productos hay en almacen
            System.out.println("\nEnter amount of products you want to buy: ");
            try {
                amountOfProducts = in.nextInt(); // cantidad de productos para comprar debe ser numerico
                in.nextLine();
                if (amountOfProducts > prod.getUnidades()) { //si no hay productos suficientes, salta excepcion
                    throw new ExceptionOutOfStock();
                }
                if ((amountOfProducts + basket.size()) > 16) throw new ExceptionBasketLimit(); //si la cantidad es mayor que 16 (cantidad maxima de la cesta),
                // salta excepcion
            } catch (InputMismatchException e1) {
                in.nextLine();
                System.err.println("Error, wrong data type");
                makeOrderMenu();
            } catch (ExceptionOutOfStock e2) {
                System.err.println("There is no sufficient products in storage");
                makeOrderMenu();
            } catch (ExceptionBasketLimit e3) {
                System.err.println("You cant buy more products then the limit of your basket");
                makeOrderMenu();
            }
            boolean prodExist = false;
            if (basket.size() < 1) { //si en la cesta aun no hay productos, añadimos productos sin verificacion
                addToBasketClones(basket, tempBasket, prod, amountOfProducts); //metodo para añadir clones a la cesta
            } else {                            // Verificamos que no haya 2 iguales productos en la cesta
                while (!prodExist) {
                    for (Producto producto : basket) {
                        if (producto.getNombre().equalsIgnoreCase(prodName)) {
                            System.out.println("\nThis product is already in the basket.");
                            prodExist = true;
                            break;
                        }
                    }
                    if (!prodExist) { //si no hay productos repetidos, añadimos productos
                        addToBasketClones(basket, tempBasket, prod, amountOfProducts);
                    }
                    prodExist = true;
                }
            }
            if (prod instanceof ProductoPerecedero) { //aqui recalculamos la cantidad de productos en almacen y calculamos el precio total del pedido
                // contando con ofertas que hay.
                prod.setUnidades(prod.getUnidades() - amountOfProducts);
                prod.escribirProductos(productosPerecederos);
                totalPrice = priceCountPerecederos(tempBasket, prod, totalPrice);
            } else if (prod instanceof ProductoNoPerecedero) {
                prod.setUnidades(prod.getUnidades() - amountOfProducts);
                prod.escribirProductos(productosNoPerecederos);
                totalPrice = offerCalculateNoPerecederos(tempBasket, prod, totalPrice, amountOfProducts);
            }
        }
    }

    private static void addToBasketClones(List<Producto> basket, List<Producto> tempBasket, Producto prod, int amountOfProducts) throws CloneNotSupportedException {
        Producto prodClone; //metodo para añadir productos a la cesta y a la cesta temporal
        if (prod instanceof ProductoPerecedero) {
            //llamamos el metodo para recalcular el precio de productos perecederos dependiendo del dias de vida
            // que quedan, lo he hecho aqui, porque debo mantener el orden, en otros casos en cesta no te muestra el precio redactado.
            prodClone = ((ProductoPerecedero) prod).clone();
            prodClone.setPrecio(((ProductoPerecedero) prod).priceCountPerecederos()); //calculo del precio
            prodClone.setUnidades(amountOfProducts);
            basket.add(prodClone);
            tempBasket.add(prodClone);
        } else if (prod instanceof ProductoNoPerecedero) {
            prodClone = ((ProductoNoPerecedero) prod).clone();
            prodClone.setUnidades(amountOfProducts);
            basket.add(prodClone);
            tempBasket.add(prodClone);
        }
    }

    private static double offerCalculateNoPerecederos(List<Producto> tempBasket, Producto prod, double totalPrice, int amountOfProducts) {
        double tempPrice = 0; //pasamos la cesta temporal, y creamos un precio temporal, cada vez que calculamos precio de un producto, se elimina toda la
        // cesta temporal y se anula el precio temporal, pero el resultado le sumamos a precio total.
        //hecho este metodo aqui por razon de utilizacion mas comoda de las listas y paso de los parametros, ademas no queria hacer muchas variables globales.
        int count = 0;
        for (int i = 0; i < amountOfProducts; i++) {
            tempPrice += prod.getPrecio();
        }
        for (Oferta oferta : ofertasList) {
            if (((ProductoNoPerecedero) prod).getIdOferta() == oferta.getIdOferta() && oferta.getTipoOferta().equals("3x2") && amountOfProducts > 2) {
                for (int j = 2; j < amountOfProducts; j += 3) {
                    count++;
                    tempPrice = tempPrice - prod.getPrecio();
                }
                System.out.println("Tienes " + count + " productos gratis por oferta 3x2");
            }
        }
        count = 0;
        for (Oferta oferta : ofertasList) {
            if (((ProductoNoPerecedero) prod).getIdOferta() == oferta.getIdOferta() && oferta.getTipoOferta().equals("2x1") && amountOfProducts > 1) {
                for (int j = 1; j < amountOfProducts; j += 2) {
                    count++;
                    tempPrice = tempPrice - prod.getPrecio();
                }
                System.out.println("Tienes " + count + " productos gratis por oferta 2x1");
            }
        }
        count = 0;
        for (Oferta oferta : ofertasList) {
            if (((ProductoNoPerecedero) prod).getIdOferta() == oferta.getIdOferta() && oferta.getTipoOferta().equals("porcentaje") && amountOfProducts > 0) {
                tempPrice = 0;
                for (int i = 0; i < amountOfProducts; i++) {
                    count++;
                    if (count <= oferta.getMaximo()) {
                        tempPrice += (prod.getPrecio() * (100 - oferta.getTantoPorciento()) / 100);
                    }
                    if (count > oferta.getMaximo()) {
                        tempPrice += prod.getPrecio();
                    }
                }
                System.out.println("Tienes " + count + " productos por precio bajado por oferta de " + oferta.getTantoPorciento() + "%");
            }
        }
        tempBasket.clear();
        return totalPrice + tempPrice;
    }

    public static double priceCountPerecederos(List<Producto> tempBasket, Producto prod, double totalPrice) {
        double tempPrice = 0; //calculo de precio de los perecederos.
        for (int i = 0; i < tempBasket.size(); i++) {
            tempPrice += prod.getPrecio();
        }
        tempBasket.clear();
        return totalPrice + tempPrice;
    }

    public static double actualProdCounter(Empleado emp, double totalPrice) {
        double actualProd; //dependiendo de empleado autorizado, calculamos productividad de empleado
        if (emp instanceof EmpleadoDiurno) {
            if (emp.getNivel() == 1) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            } else if (emp.getNivel() == 2) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            } else if (emp.getNivel() == 3) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            }
        } else if (emp instanceof EmpleadoNocturno) {
            if (emp.getNivel() == 1) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            } else if (emp.getNivel() == 2) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            } else if (emp.getNivel() == 3) {
                actualProd = emp.productivityCounter(totalPrice);
                return actualProd;
            }
        }
        return 0;
    }

    private static void checkVisual(List<Producto> basket, Empleado emp, double totalPrice) { // Metodo de factura
        if (basket.size() == 0) {
            System.out.println("\nYou don't have any products yet");
        } else {
            System.out.println("-------------Check-------------");
            System.out.println("\nPersonal data who is making order: " + emp.getNombreUsuario() + "\n");
            basketView();
            System.out.printf("\nTotal price of order is - %.2f$", totalPrice);
        }
    }

    private static void basketView() { //metodo para ver que productos tenemos en la cesta
        for (Producto producto : basket) {
            if (producto instanceof ProductoNoPerecedero) {
                for (Oferta oferta : ofertasList) {
                    if (((ProductoNoPerecedero) producto).getIdOferta() == oferta.getIdOferta()) {
                        offer = oferta;
                    }
                }
                if (offer.getTipoOferta() == (null)) {
                    System.out.println("You have " + producto.getUnidades() + " products " + producto.toString() + " with no offer");
                } else {
                    System.out.println("You have " + producto.getUnidades() + " products " + producto.toString() + " with offer - " + offer.getTipoOferta());
                }
            } else if (producto instanceof ProductoPerecedero) {
                System.out.println("You have " + producto.getUnidades() + " products " + producto.toString());
            }
        }
    }
}
