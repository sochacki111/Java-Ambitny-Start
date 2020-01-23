package io.github.mat3e.lang;

import java.util.List;
import java.util.stream.Collectors;

class LangService {

    private LangRepository repository;

    LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
//                return repository
//                .findAll()
//                .stream()
//                .map(l -> {
//                    LangDTO lang = new LangDTO();
//                    lang.setId(l.getId());
//                    lang.setCode(l.getCode());
//                    return lang;
//                }).collect(Collectors.toList());

        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());


    }


}
