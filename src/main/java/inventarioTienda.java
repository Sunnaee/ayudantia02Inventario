import java.util.Scanner; //se importa el Scanner

public class inventarioTienda {
    public static void main(String[] args) {
        menu(crearInventario());
    }

    //Función escáner.
    public static Scanner scanner(){
        return new Scanner(System.in);
    }

    //Función de matriz productos.
    public static Object[][] crearInventario(){
        Object[][] inventario = new Object[10][3];
        return inventario;
    }

    //Función para pedir el ID del producto.
    public static int pedirID(){
        int idProducto;
        while (true) {
            try {
                System.out.print("Ingrese el id del producto: ");
                idProducto = scanner().nextInt();
                break;
            } catch(Exception InputMismatchException){
                System.out.print("Entrada no válida. Ingrese un número. ");
            }
        }
        return idProducto;
    }

    //Función de pedir nombre del producto, al ser un String, se considera que el nombre pueda contener números, por lo tanto, no es necesario el try/catch.
    public static String pedirNombre(){
        System.out.print("Ingrese el nombre del producto: ");
        return scanner().nextLine();
    }

    //Función de pedir las unidades del producto.
    public static int pedirUnidades(){
        int unidadesProducto;
        while (true) {
            try {
                System.out.print("Ingrese las unidades del producto: ");
                unidadesProducto = scanner().nextInt();
                if(unidadesProducto>0){
                    break;
                } else {
                    System.out.print("Opción inválida, debe ser un valor positivo mayor a 0. Inténtelo nuevamente: ");
                }
            } catch(Exception InputMismatchException){
                System.out.print("Entrada no válida. Ingrese un número. ");
            }
        }
        return unidadesProducto;
    }

    //Función verificar si el producto existe.
    public static boolean existeProducto(Object[][] inventario, int idProducto){
        for(int i=0;i<inventario.length;i++){
            if(inventario[i][0] == null){
                return false;
            } else if((Integer)inventario[i][0] == idProducto){
                return true;
            }
        }
        return false;
    }

    //Función para obtener la fila donde está el producto según el ID.
    public static int obtenerFila(Object[][] inventario, int idProducto){
        for(int i=0;i<inventario.length;i++){
            if((Integer)inventario[i][0] == idProducto){
                return i;
            }
        }
        return 0; //la función no será llamada sin antes comprobar existeProducto(), por lo tanto el valor 0 es placeholder.
    }

    //Función agregar unidades en caso de que el producto ya exista.
    public static Object[][] agregarUnidades(Object[][] inventario, int unidadesProducto, int fila){
        inventario[fila][2] = (Integer)inventario[fila][2] + unidadesProducto;
        return inventario;
    }

    //Función para guardar un nuevo producto.
    public static Object[][] nuevoProducto(Object[][] inventario, int idProducto, String nombreProducto, int unidadesProducto){
        for(int i=0;i< inventario.length;i++){
            if(inventario[i][0] == null){
                inventario[i][0] = idProducto;
                inventario[i][1] = nombreProducto;
                inventario[i][2] = unidadesProducto;
                break;
            }
        }
        return inventario;
    }

    //Función agregar productos.
    public static Object[][] agregarProducto(Object[][] inventario, int idProducto){
        if(existeProducto(inventario, idProducto)){
            System.out.print("El producto ya existe, ¿cuántas unidades desea agregar?");
            agregarUnidades(inventario, pedirUnidades(), obtenerFila(inventario,idProducto));
        } else {
            nuevoProducto(inventario, idProducto, pedirNombre(), pedirUnidades());
        }
        return inventario;
    }

    //Función para restar unidades.
    public static Object[][] restarUnidades(Object[][] inventario, int idProducto, int unidadesProducto, int fila){
        inventario[fila][2] = (Integer) inventario[fila][2] - unidadesProducto;
        System.out.println("Se han restado "+unidadesProducto+" unidades del producto con ID "+idProducto+" correctamente.\nQuedan "+inventario[fila][2]+" unidades actualmente.");
        return inventario;
    }

    //Función de, si se restan todas las unidades, eliminar el producto.
    public static Object[][] eliminarProducto(Object[][] inventario, int fila){
        inventario[fila][0] = null;
        inventario[fila][1] = null;
        inventario[fila][2] = null;
        System.out.println("Se ha eliminado el producto correctamente.");
        return inventario;
    }

    //Función restar unidades de productos. *qué pasa si resto a uno que no existe o resto más de los que hay.
    public static Object[][] restarProductos(Object[][] inventario, int idProducto, int unidadesProducto){
        if(existeProducto(inventario,idProducto)){
            int fila = obtenerFila(inventario,idProducto);
            if(unidadesProducto > (Integer) inventario[fila][2]){
                System.out.println("No se pueden restar más unidades de las que existen.");
            } else if(unidadesProducto < (Integer) inventario[fila][2]){
                restarUnidades(inventario,idProducto,unidadesProducto,fila);
            } else if(unidadesProducto == (Integer) inventario[fila][2]){
                System.out.println("Se eliminaron todas las unidades. Se eliminará el producto completo.");
                eliminarProducto(inventario,fila);
            }
        } else {
            System.out.println("El producto no existe en el inventario.");
        }
        return inventario;
    }

    //Función de consultar disponibilidad. Devuelve cantidad disponible.
    public static int disponibilidadProducto(Object[][] inventario, int idProducto){
        if(existeProducto(inventario,idProducto)){
            return (Integer) inventario[obtenerFila(inventario,idProducto)][2];
        } else {
            System.out.println("El producto no existe en el inventario.");
        }
        return 0; //se retorna un 0 porque no está disponible, ya que los productos se eliminan automáticamente al no poseer unidades.
    }

    //Función de mostrar todos los productos (listar).
    public static void listarProductos(Object[][] inventario){
        System.out.println("LISTADO DE PRODUCTOS");
        System.out.println("===================================");
        for(int i=0;i<inventario.length;i++){
            if(inventario[i][0] == null){
                continue;
            }
            System.out.println("『 "+(i+1)+".- ID: "+inventario[i][0]+" | Nombre: "+inventario[i][1]+" | Unidades disponibles: "+inventario[i][2]+" 』");
        }
    }

    //Función imprimir opciones del menú.
    public static void mostrarOpciones(){
        System.out.println("\nMenú del inventario:");
        System.out.println("1) Agregar productos.");
        System.out.println("2) Restar productos.");
        System.out.println("3) Consultar disponibilidad de un producto.");
        System.out.println("4) Ver todos los productos.");
        System.out.println("5) Salir.");
        System.out.print("Ingrese el número de la opción que desea seleccionar: ");
    }

    //Función de leer la opción del menú.
    public static int leerOpcion(){
        int opcion;
        while (true) {
            try {
                opcion = scanner().nextInt();
                if ((opcion >= 1) && (opcion <= 5)) {
                    break;
                } else {
                    System.out.print("Opción inválida. Inténtelo nuevamente: ");
                }
            } catch(Exception InputMismatchException){
                System.out.print("Entrada no válida. Ingrese un número: ");
            }
        }
        return opcion;
    }

    //Función de ejecutar las opciones del menú.
    public static void ejecutarOpcion(Object[][] inventario, int opcion){
        if(opcion == 1){ //agregar producto.
            System.out.print("Ejecutando solicitudes para agregar producto...");
            agregarProducto(inventario,pedirID());
        } else if(opcion == 2){ //restar producto.
            System.out.print("Ejecutando solicitudes para restar unidades de producto...");
            restarProductos(inventario,pedirID(),pedirUnidades());
        } else if(opcion == 3){ //disponibilidad producto.
            System.out.println("Las unidades disponibles del producto son: "+disponibilidadProducto(inventario,pedirID()));
        } else if(opcion == 4){ //listar productos.
            listarProductos(inventario);
        } else if(opcion == 5){ //salir
            System.out.print("A continuación, confirme su salida. "); //se ejecutará el continuar en el menú.
        }
    }

    //Función booleana de continuar para el menú.
    public static boolean continuarMenu(int opcion){
        boolean continuar = true;
        if(opcion == 2){
            continuar = false;
            System.out.println("Cerrando menú... ¡Hasta luego! ^^");
        } else if(opcion != 1){
            System.out.println("Opción no válida.");
        }
        return continuar;
    }

    //Función menú.
    public static void menu(Object[][] inventario){
        boolean continuar = true;
        while(continuar){
            mostrarOpciones();
            int opcion = leerOpcion();
            ejecutarOpcion(inventario, opcion);
            System.out.print("¿Desea realizar otra operación? (1 = Sí ; 2 = No): ");
            int respuesta = scanner().nextInt();
            continuar = continuarMenu(respuesta);
        }
        scanner().close();
    }

}
