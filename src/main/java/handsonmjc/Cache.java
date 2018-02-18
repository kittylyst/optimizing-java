/*
 * 
 * 
 * 
 */
package handsonmjc;

/**
 *
 * @author kittylyst
 */
public interface Cache {

    public Integer getAge(String name);

    public void updateAge(String name, int newAge);
}
