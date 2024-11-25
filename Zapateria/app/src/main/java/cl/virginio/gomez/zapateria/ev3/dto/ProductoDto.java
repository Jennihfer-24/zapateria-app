package cl.virginio.gomez.zapateria.ev3.dto;

public class ProductoDto {

    private String codigo;
    private String color;
    private String imagen;
    private String marca;
    private String material;
    private String nombre;
    private String precio;
    private String tipo;

    public ProductoDto() {
    }

    public ProductoDto(String codigo, String color, String imagen, String marca, String material,
                       String nombre, String precio, String tipo) {
        this.codigo = codigo;
        this.color = color;
        this.imagen = imagen;
        this.marca = marca;
        this.material = material;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
