package enums;
// 	Don't use auto formatter
//	endpointName(rapidApiURL)
public enum APIsGettingEndpoints {
	type_effectiveness("https://pokemon-go1.p.rapidapi.com/type_effectiveness.json"),
	pokemon_rarity("https://pokemon-go1.p.rapidapi.com/pokemon_rarity.json"),
	pokemon_powerup_requirements("https://pokemon-go1.p.rapidapi.com/pokemon_powerup_requirements.json"),
	pokemon_genders("https://pokemon-go1.p.rapidapi.com/pokemon_genders.json"),
	current_pokemon_moves("https://pokemon-go1.p.rapidapi.com/current_pokemon_moves.json"),
	pokemon_evolutions("https://pokemon-go1.p.rapidapi.com/pokemon_evolutions.json"),
	raid_exclusive_pokemon("https://pokemon-go1.p.rapidapi.com/raid_exclusive_pokemon.json"),
	pokemon_buddy_distances("https://pokemon-go1.p.rapidapi.com/pokemon_buddy_distances.json"),
	pokemon_candy_to_evolve("https://pokemon-go1.p.rapidapi.com/pokemon_candy_to_evolve.json"),
	pokemon_stats("https://pokemon-go1.p.rapidapi.com/pokemon_stats.json"),
	nesting_pokemon("https://pokemon-go1.p.rapidapi.com/nesting_pokemon.json"),
	pokemon_max_cp("https://pokemon-go1.p.rapidapi.com/pokemon_max_cp.json"),
	shiny_pokemon("https://pokemon-go1.p.rapidapi.com/shiny_pokemon.json"),
	alolan_pokemon("https://pokemon-go1.p.rapidapi.com/alolan_pokemon.json"),
	fast_moves("https://pokemon-go1.p.rapidapi.com/fast_moves.json"),
	pokemon_names("https://pokemon-go1.p.rapidapi.com/pokemon_names.json"),
	charged_moves("https://pokemon-go1.p.rapidapi.com/charged_moves.json"),
	released_pokemon("https://pokemon-go1.p.rapidapi.com/released_pokemon.json"),
	weather_boosts("https://pokemon-go1.p.rapidapi.com/weather_boosts.json"),
	possible_ditto_pokemon("https://pokemon-go1.p.rapidapi.com/possible_ditto_pokemon.json"),
	pokemon_types("https://pokemon-go1.p.rapidapi.com/pokemon_types.json"),
	pokemon_encounter_data("https://pokemon-go1.p.rapidapi.com/pokemon_encounter_data.json");

	// declaring private variable to get values
	private String action;

	public String getAction() {
		return this.action;
	}

	// Enumeration constructor - cannot be public or protected
	private APIsGettingEndpoints(String action) {
		this.action = action;
	}
}


/* 	Java (java.net.http) (Server-side)
 	How to call APIs to update our own java JSON data
 	HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://pokemon-go1.p.rapidapi.com/type_effectiveness.json"))
		.header("x-rapidapi-host", "pokemon-go1.p.rapidapi.com")
		.header("x-rapidapi-key", "5bc8c2ef31msh38e9eedb77f0d40p1a4953jsnbc10c4d8ee51")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
 */

/*	Use this code to get the data from custom my API (Client-side)
 
  	axios.request(options).then(function (response) {
		console.log(response.data);
	}).catch(function (error) {
		console.error(error);
	});		*/