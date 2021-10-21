package com.edu.framework.atividadefisica.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.edu.framework.atividadefisica.dto.LocalidadeRepository;
import com.edu.framework.atividadefisica.dto.ModalidadeRepository;
import com.edu.framework.atividadefisica.model.Localidade;
import com.edu.framework.atividadefisica.model.Modalidade;
import com.edu.framework.atividadefisica.utils.CidadeApiHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void run(String... args) throws Exception, IOException {
        loadData();
    }

    private void loadData() {
		if (modalidadeRepository.count() == 0) {
			Modalidade corrida = new Modalidade(1L, "Corrida");
			Modalidade ciclismo = new Modalidade(2L, "Ciclismo");
			Modalidade natacao = new Modalidade(3L, "Natação");

            modalidadeRepository.save(corrida);
            modalidadeRepository.save(ciclismo);
            modalidadeRepository.save(natacao);
		}

        if(localidadeRepository.count() == 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity <String> entity = new HttpEntity<String>(headers);
            String json = restTemplate.exchange("https://servicodados.ibge.gov.br/api/v1/localidades/municipios", HttpMethod.GET, entity, String.class).getBody();
            json = json.substring(0,0) + "{\"municipios\":" + json.substring(0) + '}';
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                CidadeApiHelper cidadeApi = objectMapper.readValue(json, CidadeApiHelper.class);
                List<Localidade> localidades = new ArrayList<>();
                
                cidadeApi.getMunicipios().forEach(municipio -> {
                    Localidade localidade = new Localidade();

                    localidade.setCidade(municipio.getNome());
                    localidade.setEstado(municipio.getMicrorregiao().get("mesorregiao").get("UF").get("nome").asText());
                    localidade.setSigla(municipio.getMicrorregiao().get("mesorregiao").get("UF").get("sigla").asText());

                    localidades.add(localidade);
                });

                localidadeRepository.saveAll(localidades);

            } catch(Exception e) {
                e.printStackTrace();
            }


        }
	}
    
}
