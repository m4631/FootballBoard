package javafootballboard.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafootballboard.Model.*;

import javax.swing.JOptionPane;

public final class Archivos {

    public ArrayList<String> diccionarioJugadas;

    public Map<String, Equipo> equipos;
    public Map<String, Jugador> jugadores;
    public Map<String, Juego> juegos;
    public ArrayList<Jugada> jugadas;

    private final String stringFolder = System.getProperty("user.dir") + "\\Archivos";
    private final String stringJuegos = stringFolder + "\\Juegos.csv";
    private final String stringEquipos = stringFolder + "\\Equipos.csv";
    private final String stringJugadas = stringFolder + "\\Jugadas.csv";
    private final String stringJugadores = stringFolder + "\\Jugadores.csv";

    Path rutaFolder = Paths.get(stringFolder);

    public Archivos() {
        equipos = new HashMap<>();
        jugadores = new HashMap<>();
        juegos = new HashMap<>();
        jugadas = new ArrayList<>();
        diccionarioJugadas = new ArrayList<>();

        carpeta();
        archivoEquipos();
        archivoJugadores();
        archivoJuegos();
        archivoJugadas();

        cargarJuegos();
        cargarEquipos();
        cargarJugadores();
        cargarJugadas();
        cargarDiccionarioJugadas();
    }

    public void cargarDiccionarioJugadas() {
        diccionarioJugadas.add("Gol");
        diccionarioJugadas.add("Fuera de linea");
        diccionarioJugadas.add("Pase");
        diccionarioJugadas.add("Falta");
    }

    private void carpeta() {
        if (Files.notExists(rutaFolder)) {
            File Archivos = new File(stringFolder);
            try {
                Archivos.mkdir();
                System.out.println("Folder Creado");
            } catch (SecurityException se) {
                JOptionPane.showMessageDialog(null, "No se tiene los permisos necesarios para proceder");
            }
        } else {
            System.out.println("Ya existe el folder " + stringFolder);
        }
    }

    private void archivoEquipos() {
        if (Files.notExists(Paths.get(stringEquipos))) {
            try (FileWriter Juegos = new FileWriter(stringEquipos)) {
                Juegos.append("Nombre");
                Juegos.flush();
                System.out.println("Archivo Equipos creado");
                Juegos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ya existe el archivo " + stringEquipos);
        }
    }

    private void archivoJugadores() {
        if (Files.notExists(Paths.get(stringJugadores))) {
            try (FileWriter Juegos = new FileWriter(stringJugadores)) {
                Juegos.append("Nombre,Apellido,Posicion,Equipo");
                Juegos.flush();
                System.out.println("Archivo Jugadores creado");
                Juegos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ya existe el archivo " + stringJugadores);
        }
    }

    private void archivoJuegos() {
        if (Files.notExists(Paths.get(stringJuegos))) {
            try (FileWriter Juegos = new FileWriter(stringJuegos)) {
                Juegos.append("Cod,Titulo,EquipoA,EquipoB,Estadio,Ciudad,Fecha,Arbitro,HoraInicio,HoraFin,PuntuacionA,PuntuacionB");
                Juegos.flush();
                System.out.println("Archivo Juegos creado");
                Juegos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ya existe el archivo " + stringJuegos);
        }
    }

    private void archivoJugadas() {
        if (Files.notExists(Paths.get(stringJugadas))) {
            try (FileWriter Juegos = new FileWriter(stringJugadas)) {
                Juegos.append("Cod. de juego,Nombre de jugada,Jugador,Equipo,Hora");
                Juegos.flush();
                System.out.println("Archivo Jugadas creado");
                Juegos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ya existe el archivo " + stringJugadas);
        }
    }

    private void cargarEquipos() {
        try (BufferedReader lector = new BufferedReader(new FileReader(stringEquipos))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                equipos.put(columnas[0], new Equipo(columnas[0]));
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarJugadores() {
        try (BufferedReader lector = new BufferedReader(new FileReader(stringJugadores))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }

                Jugador jugador = new Jugador(columnas[0], columnas[1], columnas[2]);
                equipos.get(columnas[3]).agregarJugador(jugador);

                jugadores.put(columnas[0] + " " + columnas[1], jugador);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarJuegos() {
        try (BufferedReader lector = new BufferedReader(new FileReader(stringJuegos))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!equipos.containsKey(columnas[2])) {
                    equipos.put(columnas[2], new Equipo(columnas[2]));
                }
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }
                Juego juego = new Juego(columnas, equipos.get(columnas[2]), equipos.get(columnas[3]));
                juegos.put(juego.getCod(), juego);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarJugadas() {
        try (BufferedReader lector = new BufferedReader(new FileReader(stringJugadas))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!juegos.containsKey(columnas[0])) {
                    juegos.put(columnas[0], new Juego(columnas[0]));
                }
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }
                if (!jugadores.containsKey(columnas[2])) {
                    jugadores.put(columnas[2],
                            new Jugador(columnas[2].substring(0, columnas[2].indexOf(" ")),
                                    columnas[2].substring(columnas[2].indexOf(" ") + 1, columnas[2].length()), null));
                    equipos.get(columnas[3]).agregarJugador(jugadores.get(columnas[2]));
                }

                Jugada jugada = new Jugada(juegos.get(columnas[0]), columnas[1], jugadores.get(columnas[2]),
                        equipos.get(columnas[3]), columnas[4]);
                juegos.get(columnas[0]).agregarJugada(jugada);
                jugadas.add(jugada);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarEquipos(String path) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                equipos.put(columnas[0], new Equipo(columnas[0]));
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarJugadores(String path) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }

                Jugador jugador = new Jugador(columnas[0], columnas[1], columnas[2]);
                equipos.get(columnas[3]).agregarJugador(jugador);

                jugadores.put(columnas[0] + " " + columnas[1], jugador);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarJugadores(String path, Equipo equipo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!equipos.containsKey(equipo.getNombre())) {
                    equipos.put(equipo.getNombre(), equipo);
                }

                Jugador jugador = new Jugador(columnas[0], columnas[1], columnas[2]);
                equipos.get(equipo.getNombre()).agregarJugador(jugador);

                jugadores.put(columnas[0] + " " + columnas[1], jugador);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarJuegos(String path) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!equipos.containsKey(columnas[2])) {
                    equipos.put(columnas[2], new Equipo(columnas[2]));
                }
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }
                Juego juego = new Juego(columnas, equipos.get(columnas[2]), equipos.get(columnas[3]));
                juegos.put(juego.getCod(), juego);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarJugadas(String path) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");
                if (!juegos.containsKey(columnas[0])) {
                    juegos.put(columnas[0], new Juego(columnas[0]));
                }
                if (!equipos.containsKey(columnas[3])) {
                    equipos.put(columnas[3], new Equipo(columnas[3]));
                }
                if (!jugadores.containsKey(columnas[2])) {
                    jugadores.put(columnas[2],
                            new Jugador(columnas[2].substring(0, columnas[2].indexOf(" ")),
                                    columnas[2].substring(columnas[2].indexOf(" ") + 1, columnas[2].length()), null));
                    equipos.get(columnas[3]).agregarJugador(jugadores.get(columnas[2]));
                }

                Jugada jugada = new Jugada(juegos.get(columnas[0]), columnas[1], jugadores.get(columnas[2]),
                        equipos.get(columnas[3]), columnas[4]);
                juegos.get(columnas[0]).agregarJugada(jugada);
                jugadas.add(jugada);
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean cargarJugadas(String path, Juego juego, Equipo equipoa, Equipo equipob, ArrayList<Jugada> jugadas) {
        try (BufferedReader lector = new BufferedReader(new FileReader(path))) {
            String linea = "";
            lector.readLine(); //Leo el encabezado del archivo
            while ((linea = lector.readLine()) != null) {
                String columnas[] = linea.split(",");

                // Comprobar que las jugadas del archivo pertenecen a los equipos seleccionados
                if (!columnas[2].trim().equals(equipoa.getNombre().trim()) && 
                    !columnas[2].trim().equals(equipob.getNombre().trim()) ) {
                    jugadas = new ArrayList<>();
                    return false;
                }
    
                Equipo actual;
                if(columnas[2].trim().equals(equipoa.getNombre().trim())){
                    actual = equipoa;
                }else{
                    actual = equipob;
                }
                
                jugadas.add(new Jugada(juego, columnas[0], jugadores.get(columnas[1]),
                        actual, columnas[3]));
            }
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<String> convertirAEquiposCSV() {
        ArrayList<String> lineas = new ArrayList<>();
        for (Map.Entry<String, Equipo> elemento : equipos.entrySet()) {
            lineas.add(elemento.getValue().getNombre());
        }
        return lineas;
    }

    public ArrayList<String> convertirAJugadoresCSV() {
        ArrayList<String> lineas = new ArrayList<>();
        for (Map.Entry<String, Jugador> elemento : jugadores.entrySet()) {
            lineas.add(elemento.getValue().getNombre() + "," + elemento.getValue().getApellido() + ","
                    + elemento.getValue().getPosicion() + "," + elemento.getValue().getEquipo().getNombre());
        }
        return lineas;
    }

    public ArrayList<String> convertirAJuegosCSV() {
        ArrayList<String> lineas = new ArrayList<>();
        for (Map.Entry<String, Juego> elemento : juegos.entrySet()) {
            lineas.add(elemento.getValue().getCod() + "," + elemento.getValue().getTitulo() + ","
                    + elemento.getValue().getEquipoA().getNombre() + "," + elemento.getValue().getEquipoB().getNombre() + ","
                    + elemento.getValue().getEstadio() + "," + elemento.getValue().getCiudad() + "," + elemento.getValue().getFecha() + ","
                    + elemento.getValue().getArbitro() + "," + elemento.getValue().getHoraInicio() + "," + elemento.getValue().getHoraFin()
                    + "," + elemento.getValue().getPuntosA() + "," + elemento.getValue().getPuntosB()
            );

        }
        return lineas;
    }

    public ArrayList<String> convertirAJugadasCSV() {
        ArrayList<String> lineas = new ArrayList<>();
        for (Jugada jugada : jugadas) {
            lineas.add(jugada.getJuego().getCod() + "," + jugada.getNombre() + ","
                    + jugada.getJugador().getNombre() + " " + jugada.getJugador().getApellido() + "," + jugada.getEquipo().getNombre()
                    + "," + jugada.getHora());
        }
        return lineas;
    }

    public void actEquipos() throws IOException {
        ArrayList<String> equiposCSV = convertirAEquiposCSV();
        if (Files.exists(Paths.get(stringEquipos))) {
            try {
                File Equipos = new File(stringEquipos);
                Equipos.delete();
                archivoEquipos();
                FileWriter actEquipos = new FileWriter(stringEquipos);

                for (String linea : equiposCSV) {
                    actEquipos.append(linea);
                    actEquipos.flush();
                }

                actEquipos.close();

            } catch (SecurityException se) {
                JOptionPane.showMessageDialog(null, "No se tiene los permisos necesarios para proceder");
            }
        } else {
            System.out.println("Actualizado el archivo " + stringEquipos);
        }

    }

    public void actJugadores() throws IOException {
        ArrayList<String> jugadoresCSV = convertirAJugadoresCSV();
        if (Files.exists(Paths.get(stringJugadores))) {
            try {
                File Jugadores = new File(stringJugadores);
                Jugadores.delete();
                archivoJugadores();
                FileWriter actJugadores = new FileWriter(stringJugadores);

                for (String linea : jugadoresCSV) {
                    actJugadores.append(linea);
                    actJugadores.flush();
                }

                actJugadores.close();

            } catch (SecurityException se) {
                JOptionPane.showMessageDialog(null, "No se tiene los permisos necesarios para proceder");
            }
        } else {
            System.out.println("Actualizado el archivo " + stringJugadores);
        }

    }

    public void actJuegos() throws IOException {
        ArrayList<String> juegosCSV = convertirAJuegosCSV();
        if (Files.exists(Paths.get(stringJuegos))) {
            try {
                File Juegos = new File(stringJuegos);
                Juegos.delete();
                archivoJuegos();
                FileWriter actJuegos = new FileWriter(stringJuegos);

                for (String linea : juegosCSV) {
                    actJuegos.append(linea);
                    actJuegos.flush();
                }

                actJuegos.close();

            } catch (SecurityException se) {
                JOptionPane.showMessageDialog(null, "No se tiene los permisos necesarios para proceder");
            }
        } else {
            System.out.println("Actualizado el archivo " + stringJuegos);
        }

    }

    public void actJugadas() throws IOException {
        ArrayList<String> jugadasCSV = convertirAJugadasCSV();
        if (Files.exists(Paths.get(stringJugadas))) {
            try {
                File Jugadas = new File(stringJugadas);
                Jugadas.delete();
                archivoJugadas();
                FileWriter actJugadas = new FileWriter(stringJugadas);

                for (String linea : jugadasCSV) {
                    actJugadas.append(linea);
                    actJugadas.flush();
                }

                actJugadas.close();

            } catch (SecurityException se) {
                JOptionPane.showMessageDialog(null, "No se tiene los permisos necesarios para proceder");
            }
        } else {
            System.out.println("Actualizado el archivo " + stringJugadas);
        }

    }
}
