package Modelo;

public class FakeRegistroDeInscripcion implements RegistroDeInscripcion{
    private String content;

    @Override
    public void registrar(String registro) {
        this.content = registro;
    }

    public boolean startWith(String start){
        return this.content.startsWith(start);
    }
}
