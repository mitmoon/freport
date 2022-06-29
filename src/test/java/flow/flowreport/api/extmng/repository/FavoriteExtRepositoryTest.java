package flow.flowreport.api.extmng.repository;

import flow.flowreport.api.extmng.entity.FavoriteExt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class FavoriteExtRepositoryTest {

    @Autowired
    FavoriteExtRepository favoriteExtRepository;

    @Test
    public void testInsertFavoriteExtRepository()
    {
        FavoriteExt favoriteExt = FavoriteExt.builder()
                .faviExt("bat")
                .faviExtUseYn("Y")
                .build();

        favoriteExtRepository.save(favoriteExt);
    }

    @Test
    public void testChangeFavoriteExtRepository()
    {
        Set<FavoriteExt> favoriteExts = new HashSet<>();

        FavoriteExt favoriteExt1 = FavoriteExt.builder().faviExt("bat").faviExtUseYn("N").build();
        FavoriteExt favoriteExt2 = FavoriteExt.builder().faviExt("cmd").faviExtUseYn("Y").build();
        FavoriteExt favoriteExt3 = FavoriteExt.builder().faviExt("com").faviExtUseYn("Y").build();
        FavoriteExt favoriteExt4 = FavoriteExt.builder().faviExt("cpl").faviExtUseYn("Y").build();
        FavoriteExt favoriteExt5 = FavoriteExt.builder().faviExt("exe").faviExtUseYn("Y").build();
        FavoriteExt favoriteExt6 = FavoriteExt.builder().faviExt("scr").faviExtUseYn("Y").build();
        FavoriteExt favoriteExt7 = FavoriteExt.builder().faviExt("js").faviExtUseYn("N").build();

        favoriteExts.add(favoriteExt1);
        favoriteExts.add(favoriteExt2);
        favoriteExts.add(favoriteExt3);
        favoriteExts.add(favoriteExt4);
        favoriteExts.add(favoriteExt5);
        favoriteExts.add(favoriteExt6);
        favoriteExts.add(favoriteExt7);

        favoriteExtRepository.saveAll(favoriteExts);
    }
}