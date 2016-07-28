package pl.rzagorski.quizzstorm.model.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import pl.rzagorski.quizzstorm.model.database.Category;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApiCategoryTest {
    private ApiCategory apiCategory;

    @Before
    public void setUp() {
        apiCategory = new ApiCategory();
    }

    @After
    public void tearDown() {
        apiCategory = null;
    }

    @Test
    public void testApiCategoryIdTransformation1() throws Exception {
        Long testId = 0L;
        apiCategory.setId(testId);
        Category category = apiCategory.transform();
        assertTrue(testId.equals(category.getId()));
    }

    @Test
    public void testApiCategoryIdTransformation2() throws Exception {
        Long testId = null;
        apiCategory.setId(testId);
        Category category = apiCategory.transform();
        assertNull(category.getId());
    }

    @Test
    public void testApiCategoryUidTransformation1() throws Exception {
        Long testUid = 0L;
        apiCategory.setUid(testUid);
        Category category = apiCategory.transform();
        assertTrue(testUid.equals(category.getUid()));
    }

    @Test
    public void testApiCategoryUidTransformation2() throws Exception {
        Long testUid = null;
        apiCategory.setUid(testUid);
        Category category = apiCategory.transform();
        assertNull(category.getUid());
    }

    @Test
    public void testApiCategoryNameTransformation1() throws Exception {
        String testName = "AnyString";
        apiCategory.setName(testName);
        Category category = apiCategory.transform();
        assertTrue(testName.equals(category.getName()));
    }

    @Test
    public void testApiCategoryNameTransformation2() throws Exception {
        String testName = null;
        apiCategory.setName(testName);
        Category category = apiCategory.transform();
        assertNull(category.getName());
    }

    @Test
    public void testApiCategoryTypeTransformation1() throws Exception {
        String testType = "AnyString";
        apiCategory.setType(testType);
        Category category = apiCategory.transform();
        assertTrue(testType.equals(category.getType()));
    }

    @Test
    public void testApiCategoryTypeTransformation2() throws Exception {
        String testType = null;
        apiCategory.setType(testType);
        Category category = apiCategory.transform();
        assertNull(category.getName());
    }
}
