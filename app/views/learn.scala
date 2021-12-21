package views.html.learn

import play.api.libs.json.Json

import lila.api.Context
import lila.app.templating.Environment._
import lila.app.ui.ScalatagsTemplate._
import lila.common.String.html.safeJsonValue

import controllers.routes

object index {

  import trans.learn.{ play => _, _ }

  def apply(data: Option[play.api.libs.json.JsValue])(implicit ctx: Context) =
    views.html.base.layout(
      title = s"${learnShogi.txt()} - ${byPlaying.txt()}",
      moreJs = frag(
        jsModule("learn"),
        embedJsUnsafe(s"""$$(function() {
LishogiLearn(document.getElementById('learn-app'), ${safeJsonValue(
          Json.obj(
            "data" -> data,
            "i18n" -> i18nJsObject(i18nKeys)
          )
        )})})""")
      ),
      moreCss = cssTag("learn"),
      openGraph = lila.app.ui
        .OpenGraph(
          title = "Learn shogi by playing",
          description = "You don't know much about shogi? Excellent! Let's have fun and learn to play shogi!",
          url = s"$netBaseUrl${routes.Learn.index}"
        )
        .some,
      zoomable = true
    ) {
      main(id := "learn-app")
    }

  private val i18nKeys: List[lila.i18n.MessageKey] =
    List(
      learnShogi,
      byPlaying,
      menu,
      progressX,
      resetMyProgress,
      youWillLoseAllYourProgress,
      trans.learn.play,
      shogiPieces,
      theIntroduction,
      introIntro,
      introBasics,
      clickHereAfterYouveChosen,
      choosePieceDesign,
      senteGoesFirst,
      promotionZone,
      introCompleteTwo,
      theRook,
      itMovesInStraightLines,
      rookIntro,
      rookGoal,
      grabAllTheStarsRemember,
      rookPromotion,
      rookSummary,
      dragonSummaryTwo,
      grabAllTheStars,
      theFewerMoves,
      rookComplete,
      theBishop,
      itMovesDiagonally,
      bishopIntro,
      ehThereIsNoPiece,
      bishopPromotion,
      bishopSummary,
      horseSummary,
      bishopComplete,
      theKing,
      theMostImportantPiece,
      kingIntro,
      theKingIsSlow,
      kingSummary,
      kingComplete,
      theKnight,
      itMovesInAnLShape,
      knightIntro,
      knightsHaveAFancyWay,
      knightsCanJumpOverObstacles,
      knightSummary,
      pknightSummary,
      knightPromotion,
      knightComplete,
      thePawn,
      itMovesForwardOnly,
      pawnIntro,
      pawnsMoveOneSquareOnly,
      pawnPromotion,
      pawnSummary,
      tokinSummary,
      pawnComplete,
      theLance,
      itMovesStraightForward,
      lanceIntro,
      lancesAreStraighforward,
      lancePromotion,
      lanceSummary,
      planceSummaryTwo,
      lanceComplete,
      theGold,
      itMovesInAnyDirectionExceptDiagonallyBack,
      goldIntro,
      goldDoesntPromote,
      goldSummary,
      goldComplete,
      theSilver,
      itMovesEitherForwardOrDiagonallyBack,
      silverIntro,
      silverPromotion,
      silverSummary,
      psilverSummary,
      silverComplete,
      itNowPromotesToAStrongerPiece,
      selectThePieceYouWant,
      fundamentals,
      capture,
      takeTheEnemyPieces,
      captureIntro,
      takeTheBlackPieces,
      takeTheBlackPiecesAndDontLoseYours,
      takeTheEnemyPiecesAndDontLoseYours,
      captureComplete,
      pieceDrops,
      reuseCapturedPieces,
      dropIntro,
      capturedPiecesCanBeDropped,
      youCannotHaveTwoUnpromotedPawns,
      protection,
      keepYourPiecesSafe,
      protectionIntro,
      protectionComplete,
      escape,
      noEscape,
      makeSureAllSafe,
      dontForgetYouCanDropToDefend,
      dontLetThemTakeAnyUndefendedPiece,
      combat,
      captureAndDefendPieces,
      combatIntro,
      combatComplete,
      checkInOne,
      attackTheOpponentsKing,
      checkInOneIntro,
      checkInOneGoal,
      checkInOneComplete,
      outOfCheck,
      defendYourKing,
      outOfCheckIntro,
      ifYourKingIsAttacked,
      escapeWithTheKing,
      theKingCannotEscapeButBlock,
      youCanGetOutOfCheckByTaking,
      thisKnightIsCheckingThroughYourDefenses,
      getOutOfCheck,
      watchOutForYourOpponentsReply,
      escapeOrBlock,
      outOfCheckComplete,
      mateInOne,
      defeatTheOpponentsKing,
      mateInOneIntro,
      toWinInShogi,
      dropsCommonDeliverMate,
      attackYourOpponentsKing,
      chooseYourPieceCarefully,
      mateWithADroppedPawnIs,
      mateWithAPushedPawnIs,
      mateInOneComplete,
      intermediate,
      boardSetup,
      howTheGameStarts,
      boardSetupIntro,
      thisIsTheInitialPosition,
      firstPlaceTheKing,
      dropAGold,
      thenPlaceASilver,
      dropTheKnights,
      dropTheLances,
      placeTheBishopThenRook,
      placeThePawns,
      pushingThe3rdPawn,
      boardSetupComplete,
      repetition,
      fourfoldRepetitionIsADrawExcept,
      repetitionIntro,
      ifTheSamePositionOccurs,
      perpetualCheckIsALoss,
      boardFlippedFindBestMove,
      repetitionComplete,
      stalemate,
      theGameIsADraw,
      stalemateIntro,
      stalemateGoal,
      stalemateComplete,
      advanced,
      pieceValue,
      evaluatePieceStrength,
      pieceValueIntroNew,
      queenOverBishop,
      pawnsAreTheLeastValuable,
      knightSilverGold,
      goldBishopRook,
      takeAllThePiecesStartingFromTheMost,
      anExchangeIs,
      theOpponentJustGaveAway,
      yourKingsValueIsInfinite,
      twoGeneralsAreBetter,
      rememberWhichPieceIsTheMostValuable,
      rememberTheKingIsMoreValuableThanEveryOtherPieceCombined,
      checkInTwo,
      twoMovesToGiveCheck,
      checkInTwoIntro,
      checkInTwoGoal,
      checkInTwoComplete,
      whatNext,
      youKnowHowToPlayChess,
      register,
      getAFreeLishogiAccount,
      shogiResources,
      curatedShogiResources,
      practice,
      learnCommonChessPositions,
      puzzles,
      exerciseYourTacticalSkills,
      videos,
      watchInstructiveChessVideos,
      playPeople,
      opponentsFromAroundTheWorld,
      playMachine,
      testYourSkillsWithTheComputer,
      letsGo,
      stageX,
      awesome,
      excellent,
      greatJob,
      perfect,
      outstanding,
      wayToGo,
      yesYesYes,
      youreGoodAtThis,
      nailedIt,
      rightOn,
      stageXComplete,
      yourScore,
      next,
      backToMenu,
      puzzleFailed,
      retry
    ).map(_.key)
}
