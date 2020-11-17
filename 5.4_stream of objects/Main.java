package one;

import java.io.*;
import java.util.Objects;

public class Main {
    static class Animal implements Serializable {
        private final String name;

        public Animal (String name) {
            this.name = name;
        }

        @Override
        public boolean equals (Object obj) {
            if (obj instanceof Animal) {
                return Objects.equals(name, ((Animal) obj).name);
            }
            return false;
        }
    }

    /**
     * Method for serialization demo.
     * @param animals array of objects
     * @return array of bytes
     * @throws IOException
     */
    public static byte[] serializeAnimalArray (Animal[] animals) throws IOException {
        byte[] data;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeInt(animals.length);
            for (Animal animal : animals) {
                oos.writeObject(animal);
            }
            data = baos.toByteArray();
        }
        catch (InvalidClassException e) {
            throw new IllegalArgumentException(e);
        }
        catch (NotSerializableException e1) {
            throw e1;
        }
        catch (IOException e2) {
            throw e2;
        }
        return data;
    }

    /**
     * Method for deserialization demo.
     * @param data array of bytes
     * @return array of objects
     */
    public static Animal[] deserializeAnimalArray (byte[] data) {
        Animal[] animals = new Animal[] {};
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            animals = new Animal[ois.readInt()];
            for (int i = 0; i < animals.length; i++) {
                animals[i] = (Animal) ois.readObject();
            }
        }
        catch (ClassNotFoundException | InvalidClassException | StreamCorruptedException | OptionalDataException | ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        return animals;
    }

    public static void main(String[] args) throws IOException {
        byte[] data;
        Animal[] animals = new Animal[] {new Animal("cat"), new Animal("dog")};
        Animal[] animalsAfter;
        data = serializeAnimalArray(animals);
        animalsAfter = deserializeAnimalArray(data);
        for (Animal animal : animalsAfter) {
            System.out.println(animal.name);
        }
    }
}
