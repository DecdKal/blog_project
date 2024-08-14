package web_project.blog.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import web_project.blog.repository.PostRepository;
import web_project.blog.service.CategoryService;
import web_project.blog.service.PostService;
import web_project.blog.service.TagService;
import web_project.blog.service.UserService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private PostService toTest;

    @Mock
    private PostRepository mockPostRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserService mockUserService;
    @Mock
    private TagService mockTagService;
    @Mock
    private CategoryService mockCategoryService;

    @BeforeEach
    void setUp() {
        toTest = new PostServiceImpl(mockPostRepository, mockModelMapper, mockUserService, mockTagService, mockCategoryService);
    }
}
