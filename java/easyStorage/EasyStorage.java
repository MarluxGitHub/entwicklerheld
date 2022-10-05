
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class EasyStorage{

    private Map<String, String> storage = new HashMap<>();

    public void store(String item, String repository){
        if(item.trim().equals("") || repository.trim().equals("")){
            throw new IllegalArgumentException();
        }

        if(item == null || repository == null){
            throw new NullPointerException();
        }

        storage.put(item, repository);
    }

    public Map<String, String> getAllData(){
        //Implement this for scenario 2
        return this.storage;
    }

    public String getRepository(String item){
        return storage.get(item);
    }

    public Set<String> getItems(String repository){
        Set<String> items = new HashSet<>();
        for(String key : storage.keySet()){
            if(storage.get(key).equals(repository)){
                items.add(key);
            }
        }
        return items;
    }
}