package ParseElements;

public class McCabeCC {
	private int mcCabe=1;

	public int evaluate(String allText){
            int index=0;
            this.mcCabe=1;
            for(int i = 0; i < allText.length(); i++){
                    index = allText.indexOf("if(", index);
                    if(index != -1){
                            if(checkQuotes(allText, index)){
                                    mcCabe++;
                                    index+=1;
                            }
                            else index+=1;
                    }
                    else i = allText.length();
            }
            index = 0;
            for(int i = 0; i < allText.length(); i++){
                    index = allText.indexOf("do{", index);
                    if(index != -1){
                            if(checkQuotes(allText, index)){
                                    mcCabe++;
                                    index+=1;
                            }
                            else index+=1;
                    }
                    else i = allText.length();

            }

            index = 0;
            for(int i = 0; i < allText.length(); i++)
            {
                    index = allText.indexOf("while(", index);
                    if(index != -1)
                    {
                            if(checkQuotes(allText, index))
                            {

                                    mcCabe++;
                                    index+=1;
                            }
                            else
                                    index+=1;
                    }
                    else
                            i = allText.length();

            }

            index = 0;
            for(int i = 0; i < allText.length(); i++)
            {
                    index = allText.indexOf("&&", index);
                    if(index != -1)
                    {
                            if(checkQuotes(allText, index))
                            {

                                    mcCabe++;
                                    index+=1;
                            }
                            else
                                    index+=1;
                    }
                    else
                            i = allText.length();

            }

            return mcCabe;
    }

    public boolean checkQuotes(String allText, int index){
            int quote = 0;
            for(int i = 0; i < index; i++){
                    char nextChar = allText.charAt(i);
                    if(nextChar == '\"')
                            quote++;
            }

            if(quote%2==0) return true;
            else return false;

    }

}
