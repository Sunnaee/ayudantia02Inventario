import java.util.Scanner; //se importa el Scanner

public class inventarioTienda {
    public static void main(String[] args) {
        menu(inventario());
    }

    //Función escáner.
    public static Scanner scanner(){
        return new Scanner(System.in);
    }

    //Función de matriz productos.
    public static Object[][] inventario(){
        Object[][] inventario = new Object[10][3];
        return inventario;
    }

    //Función verificar si el producto existe.
    public static boolean existeProducto(Object[][] inventario, int idProducto){
        for(int i=0;i<inventario.length;i++){
            if((Integer)inventario[i][0] == idProducto){
                return true;
            }
        }
        return false;
    }

    //Función agregar unidades en caso de que el producto ya exista.
    public static Object[][] agregarUnidades(Object[][] inventario, int idProducto, int unidadesProducto){
        for(int i=0;i<inventario.length;i++){
            if((Integer)inventario[i][0] == idProducto){
                inventario[i][2] = (Integer)inventario[i][2] + unidadesProducto;
                return inventario;
            }
        }
        return inventario;
    }

    //Función para guardar un nuevo producto.
    public static Object[][] nuevoProducto(Object[][] inventario, int idProducto, String nombreProducto, int unidadesProducto){
        for(int i=0;i< inventario().length;i++){
            if(inventario[i][0] == null){
                inventario[i][0] = idProducto;
                inventario[i][1] = nombreProducto;
                inventario[i][2] = unidadesProducto;
                break;
            }
        }
        return inventario;
    }

    //Función para leer los datos del producto.
    public static void datosProducto(Object[][] inventario, int idProducto){
        String nombreProducto = scanner().next();
        int unidadesProducto = scanner().nextInt();
        nuevoProducto(inventario, idProducto, nombreProducto, unidadesProducto);
    }

    //Función agregar productos.
    public static Object[][] agregarProducto(Object[][] inventario, int idProducto){
        if(existeProducto(inventario, idProducto)){
            System.out.print("El producto ya existe, ¿cuántas unidades desea agregar?: ");
            int unidadesProducto = scanner().nextInt();
            agregarUnidades(inventario, idProducto, unidadesProducto);
        } else {
            datosProducto(inventario, idProducto);
        }
        return inventario;
    }

    //Función restar unidades de productos. *qué pasa si resto a uno que no existe o resto más de los que hay.
    //Función de, si se restan todas las unidades, eliminar el producto.
    //Función de consultar disponibilidad. Devuelve cantidad disponible.
    //Función de mostrar todos los productos (listar).

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
            Scanner scanner = scanner();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) {
                    break;
                } else {
                    System.out.print("Opción inválida. Intente nuevamente: ");
                }
            } else {
                System.out.print("Entrada no válida. Ingrese un número: ");
                scanner.next();
            }
        }
        return opcion;
    }

    //Función de ejecutar las opciones del menú.
    public static void ejecutarOpcion(int opcion, Object[][] inventario){
        System.out.println("elección: " + opcion); //provisional hasta que existan los demás métodos.
        if(opcion == 1){
            //agregar producto.
            System.out.print("Ingrese el id del producto que desea agregar: ");
            int idProducto = scanner().nextInt();
            agregarProducto(inventario, idProducto);
        } else if(opcion == 2){
            //restar producto.
        } else if(opcion == 3){
            //disponibilidad producto.
        } else if(opcion == 4){
            //listar productos.
        } else if(opcion == 5){
            //salir
            System.out.println("A continuación, confirme su salida."); //se ejecutará el continuar en el menú.
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
            ejecutarOpcion(leerOpcion(), inventario);
            System.out.print("¿Desea realizar otra operación? (1 = Sí ; 2 = No): ");
            int respuesta = scanner().nextInt();
            continuar = continuarMenu(respuesta);
        }
        scanner().close();
    }

}
