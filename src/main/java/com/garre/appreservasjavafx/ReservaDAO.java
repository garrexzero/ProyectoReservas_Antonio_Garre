package com.garre.appreservasjavafx;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public boolean crearReserva(Reserva reserva) {

        if (reserva.getHoraFin().isBefore(reserva.getHoraInicio()) || reserva.getHoraFin().equals(reserva.getHoraInicio())) {
            return false;
        }

        String sql = "INSERT INTO RESERVA (id_recurso, id_reserva_local, id_usuario, fecha, hora_inicio, hora_fin, coste, numero_plazas, motivo, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar()) {

            if (!comprobarAforo(conn, reserva.getIdRecurso(), reserva.getNumeroPlazas())) {
                return false;
            }

            if (comprobarSolapamiento(conn, reserva.getIdRecurso(), reserva.getFecha(), reserva.getHoraInicio(), reserva.getHoraFin(), -1)) {
                return false;
            }

            int idLocal = obtenerSiguienteId(conn, reserva.getIdRecurso());

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, reserva.getIdRecurso());
                pstmt.setInt(2, idLocal);
                pstmt.setInt(3, reserva.getIdUsuario());
                pstmt.setDate(4, Date.valueOf(reserva.getFecha()));
                pstmt.setTime(5, Time.valueOf(reserva.getHoraInicio()));
                pstmt.setTime(6, Time.valueOf(reserva.getHoraFin()));
                pstmt.setDouble(7, reserva.getCoste());
                pstmt.setInt(8, reserva.getNumeroPlazas());
                pstmt.setString(9, reserva.getMotivo());
                pstmt.setString(10, reserva.getObservaciones());

                int filas = pstmt.executeUpdate();
                return filas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Reserva> obtenerTodas() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdRecurso(rs.getInt("id_recurso"));
                r.setIdReservaLocal(rs.getInt("id_reserva_local"));
                r.setIdUsuario(rs.getInt("id_usuario"));
                r.setFecha(rs.getDate("fecha").toLocalDate());
                r.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                r.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                r.setCoste(rs.getDouble("coste"));
                r.setNumeroPlazas(rs.getInt("numero_plazas"));
                r.setMotivo(rs.getString("motivo"));
                r.setObservaciones(rs.getString("observaciones"));
                lista.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Reserva obtenerPorIdLocal(int idReservaLocal) {
        String sql = "SELECT * FROM RESERVA WHERE id_reserva_local = ?";
        Reserva r = null;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReservaLocal);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    r = new Reserva();
                    r.setIdRecurso(rs.getInt("id_recurso"));
                    r.setIdReservaLocal(rs.getInt("id_reserva_local"));
                    r.setIdUsuario(rs.getInt("id_usuario"));
                    r.setFecha(rs.getDate("fecha").toLocalDate());
                    r.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    r.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                    r.setCoste(rs.getDouble("coste"));
                    r.setNumeroPlazas(rs.getInt("numero_plazas"));
                    r.setMotivo(rs.getString("motivo"));
                    r.setObservaciones(rs.getString("observaciones"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public boolean actualizarReserva(Reserva reserva) {
        if (reserva.getHoraFin().isBefore(reserva.getHoraInicio()) || reserva.getHoraFin().equals(reserva.getHoraInicio())) {
            return false;
        }

        String sql = "UPDATE RESERVA SET id_recurso = ?, id_usuario = ?, fecha = ?, hora_inicio = ?, hora_fin = ?, coste = ?, numero_plazas = ?, motivo = ?, observaciones = ? WHERE id_reserva_local = ?";

        try (Connection conn = ConexionBD.conectar()) {

            if (!comprobarAforo(conn, reserva.getIdRecurso(), reserva.getNumeroPlazas())) {
                return false;
            }

            if (comprobarSolapamiento(conn, reserva.getIdRecurso(), reserva.getFecha(), reserva.getHoraInicio(), reserva.getHoraFin(), reserva.getIdReservaLocal())) {
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, reserva.getIdRecurso());
                pstmt.setInt(2, reserva.getIdUsuario());
                pstmt.setDate(3, Date.valueOf(reserva.getFecha()));
                pstmt.setTime(4, Time.valueOf(reserva.getHoraInicio()));
                pstmt.setTime(5, Time.valueOf(reserva.getHoraFin()));
                pstmt.setDouble(6, reserva.getCoste());
                pstmt.setInt(7, reserva.getNumeroPlazas());
                pstmt.setString(8, reserva.getMotivo());
                pstmt.setString(9, reserva.getObservaciones());
                pstmt.setInt(10, reserva.getIdReservaLocal());

                int filas = pstmt.executeUpdate();
                return filas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean borrarReserva(int idReservaLocal) {
        String sql = "DELETE FROM RESERVA WHERE id_reserva_local = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReservaLocal);
            int filas = pstmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int obtenerSiguienteId(Connection conn, int idRecurso) throws SQLException {
        String sql = "SELECT MAX(id_reserva_local) FROM RESERVA WHERE id_recurso = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRecurso);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) + 1;
                }
            }
        }
        return 1;
    }

    private boolean comprobarAforo(Connection conn, int idRecurso, int plazasPedidas) throws SQLException {
        String sql = "SELECT capacidad FROM RECURSO WHERE id_recurso = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRecurso);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int capacidadMax = rs.getInt("capacidad");
                    if (plazasPedidas > capacidadMax) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean comprobarSolapamiento(Connection conn, int idRecurso, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int ignorarId) throws SQLException {
        String sql = "SELECT * FROM RESERVA WHERE id_recurso = ? AND fecha = ? AND ? < hora_fin AND ? > hora_inicio AND id_reserva_local != ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRecurso);
            pstmt.setDate(2, Date.valueOf(fecha));
            pstmt.setTime(3, Time.valueOf(horaInicio));
            pstmt.setTime(4, Time.valueOf(horaFin));
            pstmt.setInt(5, ignorarId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}