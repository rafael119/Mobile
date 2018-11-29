package com.example.danielmezam;

public class medicamentos {
    private String nombreMedicamento;
    private String padecimiento;
    private String dosis;
    private String imagenEnvase;
    private String imagenMedicamento;
    private String descripcion;
    private String doctor;
    private String horasAldia;
    private String fechaInicio;
    private String fechaTermino;
    private String horaInicial;

    public medicamentos(String nombreMedicamento, String padecimiento, String dosis, String imagenEnvase, String imagenMedicamento, String descripcion, String doctor, String horasAldia, String fechaInicio, String fechaTermino, String horaInicial) {
        this.nombreMedicamento = nombreMedicamento;
        this.padecimiento = padecimiento;
        this.dosis = dosis;
        this.imagenEnvase = imagenEnvase;
        this.imagenMedicamento = imagenMedicamento;
        this.descripcion = descripcion;
        this.doctor = doctor;
        this.horasAldia = horasAldia;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.horaInicial = horaInicial;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getImagenEnvase() {
        return imagenEnvase;
    }

    public void setImagenEnvase(String imagenEnvase) {
        this.imagenEnvase = imagenEnvase;
    }

    public String getImagenMedicamento() {
        return imagenMedicamento;
    }

    public void setImagenMedicamento(String imagenMedicamento) {
        this.imagenMedicamento = imagenMedicamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHorasAldia() {
        return horasAldia;
    }

    public void setHorasAldia(String horasAldia) {
        this.horasAldia = horasAldia;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }
}