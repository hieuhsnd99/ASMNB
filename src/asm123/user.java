
package asm123;

/**
 *
 * @author Pham Trung Hieu
 */
public class user {
    
    private String name;
    private String diemso;
    
    public user(String name, String diemso){
        this.name = name;
        this.diemso = diemso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiem() {
        return diemso;
    }

    public void setDiem(String diemso) {
        this.diemso = diemso;
    }
    
    @Override
    public String toString(){
        return name + "     " + diemso;
    }
    
}
