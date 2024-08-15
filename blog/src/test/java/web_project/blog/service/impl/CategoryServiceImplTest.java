package web_project.blog.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import web_project.blog.model.dto.CategoryDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.UserEntity;
import web_project.blog.repository.CategoryRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    private CategoryServiceImpl toTest;

    @Captor
    private ArgumentCaptor<CategoryEntity> categoryEntityCaptor;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @BeforeEach
    void setUp() {

        toTest = new CategoryServiceImpl(
                mockCategoryRepository,
                new ModelMapper()
        );
    }

    @Test
    void testCategoryCreation() {

        CategoryDTO categoryDTO = new CategoryDTO("Test Name", "Test Description");

        toTest.add(categoryDTO);

        Mockito.verify(mockCategoryRepository).save(categoryEntityCaptor.capture());

        CategoryEntity actualSavedEntity = categoryEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(categoryDTO.getName(), actualSavedEntity.getName());
        Assertions.assertEquals(categoryDTO.getDescription(), actualSavedEntity.getDescription());
    }
}
