package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    private Lottos lottos;
    private Lotto winningLotto;

    @BeforeEach
    public void createLottos() {
        winningLotto = new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12);
        lottos = new Lottos(
                Arrays.asList(
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 11), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 12),
                        new Lotto(Arrays.asList(1, 7, 8, 9, 10, 13), 14)
                )
        );
    }

    @Test
    public void calculateNumberOfRank() {
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIRST, winningLotto))
                .isEqualTo(new BigDecimal("3"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.SECOND, winningLotto))
                .isEqualTo(new BigDecimal("2"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.THIRD, winningLotto))
                .isEqualTo(new BigDecimal("1"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.FOURTH, winningLotto))
                .isEqualTo(new BigDecimal("0"));
        assertThat(lottos.calculateNumberOfRank(LottoRank.FIFTH, winningLotto))
                .isEqualTo(new BigDecimal("0"));
    }

    @Test
    public void getLottoStatisticsTest() {
        LottoStatistics lottoStatistics = lottos.getLottoStatistics(winningLotto, 5);
        HashMap<LottoRank, BigDecimal> rankNumbers = lottoStatistics.getRankNumbers();
        HashMap<LottoRank, BigDecimal> trueRankNumbers = new HashMap<>();
        trueRankNumbers.put(LottoRank.FIRST, new BigDecimal("3"));
        trueRankNumbers.put(LottoRank.SECOND, new BigDecimal("2"));
        trueRankNumbers.put(LottoRank.THIRD, new BigDecimal("1"));
        trueRankNumbers.put(LottoRank.FOURTH, new BigDecimal("0"));
        trueRankNumbers.put(LottoRank.FIFTH, new BigDecimal("0"));
        assertThat(trueRankNumbers).isEqualTo(rankNumbers);
    }
}
