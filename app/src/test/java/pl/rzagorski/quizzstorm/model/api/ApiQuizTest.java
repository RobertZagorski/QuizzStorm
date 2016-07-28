package pl.rzagorski.quizzstorm.model.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import pl.rzagorski.quizzstorm.model.database.Category;
import pl.rzagorski.quizzstorm.model.database.Photo;
import pl.rzagorski.quizzstorm.model.database.Quiz;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ApiQuizTest {
    private ApiQuiz apiQuiz;

    @Before
    public void setUp() {
        apiQuiz = new ApiQuiz();

        ApiCategory apiCategory = Mockito.mock(ApiCategory.class);
        when(apiCategory.transform()).thenReturn(null);
        apiQuiz.setCategory(apiCategory);

        ApiPhoto apiPhoto = Mockito.mock(ApiPhoto.class);
        when(apiPhoto.transform()).thenReturn(null);
        apiQuiz.setMainPhoto(apiPhoto);
    }

    @After
    public void tearDown() {
        apiQuiz = null;
    }

    @Test
    public void testApiQuizTransformationId1() throws Exception {
        Long testId = 0L;
        apiQuiz.setId(testId);
        Quiz quiz = apiQuiz.transform();
        assertTrue(testId.equals(quiz.getId()));
    }

    @Test
    public void testApiQuizTransformationId2() throws Exception {
        Long testId = null;
        apiQuiz.setId(testId);
        Quiz quiz = apiQuiz.transform();
        assertNull(quiz.getId());
    }

    @Test
    public void testAPiQuizTitleTransformation1() throws Exception {
        String testString = "AnyString";
        apiQuiz.setTitle(testString);
        Quiz quiz = apiQuiz.transform();
        assertTrue(testString.equals(quiz.getTitle()));

    }

    @Test
    public void testApiQuizTitleTransformation2() throws Exception {
        String testString = null;
        apiQuiz.setTitle(testString);
        Quiz quiz = apiQuiz.transform();
        assertNull(quiz.getTitle());
    }

    @Test
    public void testApiQuizPhotoTransformation1() throws Exception {
        ApiPhoto apiPhotoMock = mock(ApiPhoto.class);
        when(apiPhotoMock.transform())
                .thenReturn(mock(Photo.class));
        apiQuiz.setMainPhoto(apiPhotoMock);
        apiQuiz.transform();
        verify(apiPhotoMock).transform();
    }

    @Test
    public void testApiQuizPhotoTransformation2() throws Exception {
        ApiPhoto apiPhotoMock = mock(ApiPhoto.class);
        Photo photoMock = mock(Photo.class);
        when(apiPhotoMock.transform())
                .thenReturn(photoMock);
        apiQuiz.setMainPhoto(apiPhotoMock);
        Quiz quiz = apiQuiz.transform();
        verify(photoMock, atLeastOnce());
    }

    @Test
    public void testApiQuizCategoryTransformation1() throws Exception {
        ApiCategory apiCategoryMock = mock(ApiCategory.class);
        when(apiCategoryMock.transform()).thenReturn(mock(Category.class));
        List<ApiCategory> apiCategoryList = new ArrayList<>();
        apiCategoryList.add(apiCategoryMock);
        apiQuiz.setCategory(apiCategoryMock);
        apiQuiz.setCategories(apiCategoryList);

        Quiz quiz = apiQuiz.transform();

        verify(apiCategoryMock, atLeastOnce()).transform();
    }

    @Test
    public void testApiQuizCategoryTransformation2() throws Exception {
        ApiCategory apiCategoryMock = mock(ApiCategory.class);
        when(apiCategoryMock.transform()).thenReturn(mock(Category.class));
        List<ApiCategory> apiCategoryList = new ArrayList<>();
        apiCategoryList.add(apiCategoryMock);
        apiQuiz.setCategory(apiCategoryMock);
        apiQuiz.setCategories(apiCategoryList);

        Quiz quiz = apiQuiz.transform();

        verify(apiCategoryMock, times(2)).transform();
    }

    @Test
    public void testApiQuizCategoryTransformation3() throws Exception {
        ApiCategory apiCategoryMock = mock(ApiCategory.class);
        Category categoryMock = mock(Category.class);
        when(apiCategoryMock.transform()).thenReturn(categoryMock);
        List<ApiCategory> apiCategoryList = new ArrayList<>();
        apiCategoryList.add(apiCategoryMock);
        apiQuiz.setCategory(apiCategoryMock);
        apiQuiz.setCategories(apiCategoryList);

        Quiz quiz = apiQuiz.transform();

        verify(categoryMock, times(1)).getName();
    }

    @Test
    public void testApiQuizCategoryTransformation4() throws Exception {
        ApiCategory apiCategoryMock = mock(ApiCategory.class);
        Category categoryMock = mock(Category.class);
        when(apiCategoryMock.transform()).thenReturn(categoryMock);
        List<ApiCategory> apiCategoryList = new ArrayList<>();
        apiCategoryList.add(apiCategoryMock);
        apiQuiz.setCategory(apiCategoryMock);
        apiQuiz.setCategories(apiCategoryList);

        Quiz quiz = apiQuiz.transform();

        verify(categoryMock, times(1)).getType();
    }

    @Test
    public void testApiQuizCategoryTransformation5() throws Exception {
        ApiCategory apiCategoryMock = mock(ApiCategory.class);
        Category categoryMock = mock(Category.class);
        when(apiCategoryMock.transform()).thenReturn(categoryMock);
        List<ApiCategory> apiCategoryList = new ArrayList<>();
        apiCategoryList.add(apiCategoryMock);
        apiQuiz.setCategory(apiCategoryMock);
        apiQuiz.setCategories(apiCategoryList);

        Quiz quiz = apiQuiz.transform();

        verify(categoryMock, times(1)).getUid();
    }
}
