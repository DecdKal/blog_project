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
import web_project.blog.model.dto.TagDTO;
import web_project.blog.model.entity.CategoryEntity;
import web_project.blog.model.entity.TagEntity;
import web_project.blog.repository.TagRepository;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    private TagServiceImpl toTest;

    @Captor
    private ArgumentCaptor<TagEntity> tagEntityCaptor;

    @Mock
    private TagRepository mockTagRepository;

    @BeforeEach
    void setUp() {

        toTest = new TagServiceImpl(
                mockTagRepository,
                new ModelMapper()
        );
    }

    @Test
    void testTagCreation() {

        TagDTO tagDTO = new TagDTO("Test Name");

        toTest.add(tagDTO);

        Mockito.verify(mockTagRepository).save(tagEntityCaptor.capture());

        TagEntity actualSavedEntity = tagEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(tagDTO.getName(), actualSavedEntity.getName());
    }
}
