package com.edu.framework.atividadefisica.loader;

import com.edu.framework.atividadefisica.dto.LocalidadeRepository;
import com.edu.framework.atividadefisica.dto.ModalidadeRepository;
import com.edu.framework.atividadefisica.model.Localidade;
import com.edu.framework.atividadefisica.model.Modalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Override
    public void run(String... args) throws Exception {
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
            Localidade novaAndradina = new Localidade("Mato Grosso do Sul", "Nova Andradina", "MS");
            Localidade ivinhema = new Localidade("Mato Grosso do Sul", "Ivinhema", "MS");
            localidadeRepository.save(novaAndradina);
            localidadeRepository.save(ivinhema);
        }


	}

    
}
