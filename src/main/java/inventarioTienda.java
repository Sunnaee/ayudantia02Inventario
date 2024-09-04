import java.util.Scanner; //se importa el Scanner

public class inventarioTienda {
    public static void main(String[] args) {
        menu();
    }

    //Función escáner.
    public static Scanner scanner(){
        return new Scanner(System.in);
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
    public static void ejecutarOpcion(int opcion){
        System.out.println("elección: " + opcion); //provisional hasta que existan los demás métodos.
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
    public static void menu(){
        boolean continuar = true;
        while(continuar){
            mostrarOpciones();
            ejecutarOpcion(leerOpcion());
            System.out.print("¿Desea realizar otra operación? (1 = Sí ; 2 = No): ");
            int respuesta = scanner().nextInt();
            continuar = continuarMenu(respuesta);
        }
        scanner().close();
    }

    //Función de matriz productos.
    public static Object[][] inventario(){
        Object[][] inventario = new Object[10][3];
        return inventario;
    }

    //Función agregar productos.
    //Función verificar si el producto existe.
    //Función agregar unidades en caso de que el producto ya exista.
    //Función restar unidades de productos. *qué pasa si elimino uno que no existe.
    //Función de, si se restan todas las unidades, eliminar el producto.
    //Función de consultar disponibilidad.
    //Función de mostrar todos los productos (listar).

}
