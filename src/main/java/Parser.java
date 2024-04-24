import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public static List<Country> sortByName()
    {
        List<Country> sortedByName = countries;
        Collections.sort(sortedByName, Comparator.comparing(Country::getName));
        return  sortedByName;
    }

    public static List<Country> sortByPopulation()
    {
        List<Country> sortedByPopulation = countries;
        Collections.sort(sortedByPopulation, Comparator.comparing(Country::getPopulation));
        Collections.reverse(sortedByPopulation);
        return sortedByPopulation;
    }

    public static List<Country> sortByArea()
    {
        List<Country> sortedByArea = countries;
        Collections.sort(sortedByArea, Comparator.comparing(Country::getArea));
        Collections.reverse(sortedByArea);
        return  sortedByArea ;
    }

    public static void setUp() throws IOException
    {
        try {
            File HTMLFile = new File("src\\Resources\\country-list.html");
            Document Doc1 = Jsoup.parse(HTMLFile, "UTF-8");
            Elements Countries = Doc1.select(".country");

            for (Element country : Countries)
            {
                try
                {
                    int population = Integer.parseInt(country.select(".country-population").text());
                    double area = Double.parseDouble(country.select(".country-area").text());
                    String countryName = country.select(".country-name").text();
                    String capital = country.select(".country-capital").text();
                    countries.add(new Country(countryName, capital, population, area));
                }
                catch (NumberFormatException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException
    {
    }
}