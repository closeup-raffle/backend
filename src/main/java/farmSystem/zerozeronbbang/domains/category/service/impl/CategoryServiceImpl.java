package farmSystem.zerozeronbbang.domains.category.service.impl;

import farmSystem.zerozeronbbang.domains.category.Category;
import farmSystem.zerozeronbbang.domains.category.dto.ResFindCategoriseDto;
import farmSystem.zerozeronbbang.domains.category.repository.CategoryRepository;
import farmSystem.zerozeronbbang.domains.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResFindCategoriseDto> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<ResFindCategoriseDto> resFindCategoriseDtos = categories.stream()
                .map(ResFindCategoriseDto::new)
                .collect(Collectors.toList());

        return resFindCategoriseDtos;
    }
}
