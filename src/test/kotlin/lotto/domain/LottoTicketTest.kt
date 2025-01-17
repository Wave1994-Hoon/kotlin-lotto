package lotto.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import lotto.domain.exception.LottoSizeMismatchException

class LottoTicketTest : DescribeSpec({
    describe("init 메서드 테스트") {
        context("로또 숫자가 6개가 주어질 때") {
            it("객체를 성공적으로 생성한다.") {
                LottoTicket.of(setOf(1, 2, 3, 4, 5, 6))
            }
        }

        context("로또 숫자가 6개 이상 주어질 때") {
            it("예외를 던진다.") {
                shouldThrow<LottoSizeMismatchException> {
                    val givenNumbers: Set<Int> = setOf(1, 2, 3, 4, 5, 6, 7)
                    LottoTicket.of(givenNumbers)
                }
            }
        }

        context("로또 숫자가 6개 미만 주어질 때") {
            it("예외를 던진다.") {
                shouldThrow<LottoSizeMismatchException> {
                    val givenNumbers: Set<Int> = setOf(1, 2, 3, 4, 5)
                    LottoTicket.of(givenNumbers)
                }
            }
        }
    }

    describe("isMatch 메서드") {
        context("보너스 매치 타입일 때, ") {
            val givenLottoTicket = LottoTicket.of(setOf(1, 2, 3, 4, 5, 33))

            it("매치 카운트가 일치하면 true 를 반환한다.") {
                val givenBonusNumber: Int = 33
                val givenWinningNumber = LottoWinningNumber(LottoTicket.of(setOf(1, 2, 3, 4, 5, 6)), LottoNumber.of(givenBonusNumber))
                givenLottoTicket.isMatch(LottoRank.SECOND, givenWinningNumber) shouldBe true
            }

            it(" 매치 카운트가 불일치하면 false 를 반환한다.") {
                val givenBonusNumber: Int = 7
                val givenWinningNumber = LottoWinningNumber(LottoTicket.of(setOf(1, 2, 3, 4, 5, 6)), LottoNumber.of(givenBonusNumber))
                givenLottoTicket.isMatch(LottoRank.SECOND, givenWinningNumber) shouldBe false
            }
        }

        context("보너스 매치 타입이 아닐 때") {
            val givenLottoTicket = LottoTicket.of(setOf(1, 2, 3, 11, 22, 33))
            val givenWinningNumber = LottoWinningNumber(LottoTicket.of(setOf(1, 2, 3, 4, 5, 6)), LottoNumber.of(7))

            it("매치 카운트가 일치하면 true 를 반환한다.") {
                givenLottoTicket.isMatch(LottoRank.FIFTH, givenWinningNumber) shouldBe true
            }

            it("매치 카운트가 불일치하면 false 를 반환한다.") {
                givenLottoTicket.isMatch(LottoRank.FIRST, givenWinningNumber) shouldBe false
            }
        }
    }
})
