public class card {
    private Stirng definition;
    private String term;
}

public card(String definiton,String term){
    if(definiton==null || definition.isEmpty()||term==null||term.isEmpty()){
        throw new IllegalArguementException("definiton and term must be non-empty");
        this.definition=definition;
        this.term=term;
    }
}

public string toString(){
    return definition;
}
@Override
public boolean attempt(String response)
{
    return (response.equalsIgnoreCase(term));
}

public string getTerm(){
    return term;
}

