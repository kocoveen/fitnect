<template>
  <div class="accordion" id="accordionPanelsStayOpenExample">
    <!-- Filter Section -->
    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingOne">
        <button
          class="accordion-button"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#panelsStayOpen-collapseOne"
          aria-expanded="true"
          aria-controls="panelsStayOpen-collapseOne"
        >
          필터
        </button>
      </h2>
      <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
        <div class="accordion-body">
          <div class="filter-option">
            <div>
              <input type="radio" name="sort" value="name" @change="onSortChange" />
              <label class="check-label">이름 순</label>
            </div>
            <div>
              <input type="radio" name="sort" value="reviews" @change="onSortChange" />
              <label class="check-label">리뷰 순</label>
            </div>
            <div>
              <input type="radio" name="sort" value="rating" @change="onSortChange" />
              <label class="check-label">평점 순</label>
            </div>
          </div>
          <div class="filter-option">
            <div>
              <input type="radio" name="sort" value="members" @change="onSortChange" />
              <label class="check-label">멤버 순</label>
            </div>
            <div>
              <input type="radio" name="sort" value="price" @change="onSortChange" />
              <label class="check-label">가격 순</label>
            </div>
            <div>
              <input type="radio" name="sort" value="distance" @change="onSortChange" />
              <label class="check-label">거리 순</label>
            </div>
          </div>
        </div>
      </div>
    </div>

    <hr style="margin: 0" />

    <!-- Facility Section -->
    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
        <button
          class="accordion-button"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#panelsStayOpen-collapseTwo"
          aria-expanded="false"
          aria-controls="panelsStayOpen-collapseTwo"
        >
          편의 시설
        </button>
      </h2>
      <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
        <div class="accordion-body">
          <div class="filter-option">
            <div>
              <input type="checkbox" @change="onFacilityChange('freeParking')" />
              <label class="check-label">무료 주차</label>
            </div>
            <div>
              <input type="checkbox" @change="onFacilityChange('paidParking')" />
              <label class="check-label">유료 주차</label>
            </div>
            <div>
              <input type="checkbox" @change="onFacilityChange('open24hours')" />
              <label class="check-label">24시간</label>
            </div>
          </div>
          <div class="filter-option">
            <div>
              <input type="checkbox" @change="onFacilityChange('showerFacilities')" />
              <label class="check-label">샤워 시설</label>
            </div>
            <div>
              <input type="checkbox" @change="onFacilityChange('sharedLockers')" />
              <label class="check-label">공유 락커</label>
            </div>
            <div>
              <input type="checkbox" @change="onFacilityChange('privateLockers')" />
              <label class="check-label">개인락커</label>
            </div>
          </div>
          <div class="filter-option">
            <div>
              <input type="checkbox" @change="onFacilityChange('unmannedStore')" />
              <label class="check-label">무인 점포</label>
            </div>
          </div>
        </div>
      </div>
    </div>

    <hr style="margin: 0" />

    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingThree">
        <button
          class="accordion-button"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#panelsStayOpen-collapseThree"
          aria-expanded="false"
          aria-controls="panelsStayOpen-collapseThree"
        >
          운동 종목
        </button>
      </h2>
      <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
        <div class="accordion-body">
          <div class="filter-option">
            <div>
              <input type="radio" name="exercise" value="수영" @change="onTypeChange" />
              <label class="check-label">수영</label>
            </div>
            <div>
              <input type="radio" name="exercise" value="헬스" @change="onTypeChange" />
              <label class="check-label">헬스</label>
            </div>
            <div>
              <input type="radio" name="exercise" value="농구" @change="onTypeChange" />
              <label class="check-label">농구</label>
            </div>
          </div>
          <div class="filter-option">
            <div>
              <input type="radio" name="exercise" value="필라테스" @change="onTypeChange" />
              <label class="check-label">필라테스</label>
            </div>
            <div>
              <input type="radio" name="exercise" value="클라이밍" @change="onTypeChange" />
              <label class="check-label">클라이밍</label>
            </div>
            <div>
              <input type="radio" name="exercise" value="복싱" @change="onTypeChange" />
              <label class="check-label">복싱</label>
            </div>
          </div>
          <div class="filter-option">
            <div>
              <input type="radio" name="exercise" value="배드민턴" @change="onTypeChange" />
              <label class="check-label">배드민턴</label>
            </div>
          </div>
        </div>
      </div>
    </div>

    <hr style="margin: 0" />

    <!-- Exercise Equipment Section -->
    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingFour">
        <button
          class="accordion-button"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#panelsStayOpen-collapseFour"
          aria-expanded="true"
          aria-controls="panelsStayOpen-collapseFour"
        >
          운동 기구
        </button>
      </h2>
      <div id="panelsStayOpen-collapseFour" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingFour">
        <div class="accordion-body">
          <!-- 조건부 렌더링 추가 -->
          <div v-if="selectedExercise === ''" class="filter-option option-text">
            <p>운동을 선택해주세요</p>
          </div>

          <!-- Swimming Equipment -->
          <div v-if="selectedExercise === '수영'" class="filter-option">
            <div>
              <input type="checkbox" id="swimming-equip1" />
              <label for="swimming-equip1" class="check-label">잠수복</label>
            </div>
            <div>
              <input type="checkbox" id="swimming-equip2" />
              <label for="swimming-equip2" class="check-label">구명 조끼</label>
            </div>
            <div>
              <input type="checkbox" id="swimming-equip3" />
              <label for="swimming-equip3" class="check-label">수영복</label>
            </div>
          </div>
          <div v-if="selectedExercise === '수영'" class="filter-option">
            <div>
              <input type="checkbox" id="swimming-equip4" />
              <label for="swimming-equip4" class="check-label">수영 모자</label>
            </div>
            <div>
              <input type="checkbox" id="swimming-equip5" />
              <label for="swimming-equip5" class="check-label">비치볼</label>
            </div>
            <div>
              <input type="checkbox" id="swimming-equip6" />
              <label for="swimming-equip6" class="check-label">오리발</label>
            </div>
          </div>

          <!-- Gym Equipment -->
          <div v-if="selectedExercise === '헬스'" class="filter-option">
            <div>
              <input type="checkbox" id="gym-equip1" />
              <label for="gym-equip1" class="check-label">덤벨</label>
            </div>
            <div>
              <input type="checkbox" id="gym-equip2" />
              <label for="gym-equip2" class="check-label">바벨</label>
            </div>
            <div>
              <input type="checkbox" id="gym-equip3" />
              <label for="gym-equip3" class="check-label">벤치 프레스</label>
            </div>
          </div>
          <div v-if="selectedExercise === '헬스'" class="filter-option">
            <div>
              <input type="checkbox" id="gym-equip4" />
              <label for="gym-equip4" class="check-label">러닝 머신</label>
            </div>
            <div>
              <input type="checkbox" id="gym-equip5" />
              <label for="gym-equip5" class="check-label">케이블머신</label>
            </div>
            <div>
              <input type="checkbox" id="gym-equip6" />
              <label for="gym-equip6" class="check-label">스쿼트랙</label>
            </div>
          </div>

          <!-- Basketball Equipment -->
          <div v-if="selectedExercise === '농구'" class="filter-option">
            <div>
              <input type="checkbox" id="basketball-equip1" />
              <label for="basketball-equip1" class="check-label">농구공</label>
            </div>
            <div>
              <input type="checkbox" id="basketball-equip2" />
              <label for="basketball-equip2" class="check-label">골대</label>
            </div>
            <div>
              <input type="checkbox" id="basketball-equip3" />
              <label for="basketball-equip3" class="check-label">유니폼</label>
            </div>
          </div>
          <div v-if="selectedExercise === '농구'" class="filter-option">
            <div>
              <input type="checkbox" id="basketball-equip4" />
              <label for="basketball-equip4" class="check-label">신발</label>
            </div>
            <div>
              <input type="checkbox" id="basketball-equip5" />
              <label for="basketball-equip5" class="check-label">타이머</label>
            </div>
            <div>
              <input type="checkbox" id="basketball-equip6" />
              <label for="basketball-equip6" class="check-label">작전판</label>
            </div>
          </div>

          <!-- Pilates Equipment -->
          <div v-if="selectedExercise === '필라테스'" class="filter-option">
            <div>
              <input type="checkbox" id="pilates-equip1" />
              <label for="pilates-equip1" class="check-label">매트</label>
            </div>
            <div>
              <input type="checkbox" id="pilates-equip2" />
              <label for="pilates-equip2" class="check-label">링</label>
            </div>
            <div>
              <input type="checkbox" id="pilates-equip3" />
              <label for="pilates-equip3" class="check-label">밴드</label>
            </div>
          </div>
          <div v-if="selectedExercise === '필라테스'" class="filter-option">
            <div>
              <input type="checkbox" id="pilates-equip4" />
              <label for="pilates-equip4" class="check-label">볼</label>
            </div>
            <div>
              <input type="checkbox" id="pilates-equip5" />
              <label for="pilates-equip5" class="check-label">롤러</label>
            </div>
            <div>
              <input type="checkbox" id="pilates-equip6" />
              <label for="pilates-equip6" class="check-label">체어</label>
            </div>
          </div>

          <!-- Climbing Equipment -->
          <div v-if="selectedExercise === '클라이밍'" class="filter-option">
            <div>
              <input type="checkbox" id="climbing-equip1" />
              <label for="climbing-equip1" class="check-label">로프</label>
            </div>
            <div>
              <input type="checkbox" id="climbing-equip2" />
              <label for="climbing-equip2" class="check-label">하네스</label>
            </div>
            <div>
              <input type="checkbox" id="climbing-equip3" />
              <label for="climbing-equip3" class="check-label">초크백</label>
            </div>
          </div>
          <div v-if="selectedExercise === '클라이밍'" class="filter-option">
            <div>
              <input type="checkbox" id="climbing-equip4" />
              <label for="climbing-equip4" class="check-label">암벽화</label>
            </div>
            <div>
              <input type="checkbox" id="climbing-equip5" />
              <label for="climbing-equip5" class="check-label">캠</label>
            </div>
            <div>
              <input type="checkbox" id="climbing-equip6" />
              <label for="climbing-equip6" class="check-label">피톤</label>
            </div>
          </div>

          <!-- Boxing Equipment -->
          <div v-if="selectedExercise === '복싱'" class="filter-option">
            <div>
              <input type="checkbox" id="boxing-equip1" />
              <label for="boxing-equip1" class="check-label">글러브</label>
            </div>
            <div>
              <input type="checkbox" id="boxing-equip2" />
              <label for="boxing-equip2" class="check-label">미트</label>
            </div>
            <div>
              <input type="checkbox" id="boxing-equip3" />
              <label for="boxing-equip3" class="check-label">샌드백</label>
            </div>
          </div>
          <div v-if="selectedExercise === '복싱'" class="filter-option">
            <div>
              <input type="checkbox" id="boxing-equip4" />
              <label for="boxing-equip4" class="check-label">헤드기어</label>
            </div>
            <div>
              <input type="checkbox" id="boxing-equip5" />
              <label for="boxing-equip5" class="check-label">링</label>
            </div>
            <div>
              <input type="checkbox" id="boxing-equip6" />
              <label for="boxing-equip6" class="check-label">타이머</label>
            </div>
          </div>

          <!-- Badminton Equipment -->
          <div v-if="selectedExercise === '배드민턴'" class="filter-option">
            <div>
              <input type="checkbox" id="badminton-equip1" />
              <label for="badminton-equip1" class="check-label">라켓</label>
            </div>
            <div>
              <input type="checkbox" id="badminton-equip2" />
              <label for="badminton-equip2" class="check-label">셔틀콕</label>
            </div>
            <div>
              <input type="checkbox" id="badminton-equip3" />
              <label for="badminton-equip3" class="check-label">네트</label>
            </div>
          </div>
          <div v-if="selectedExercise === '배드민턴'" class="filter-option">
            <div>
              <input type="checkbox" id="badminton-equip4" />
              <label for="badminton-equip4" class="check-label">유니폼</label>
            </div>
            <div>
              <input type="checkbox" id="badminton-equip5" />
              <label for="badminton-equip5" class="check-label">신발</label>
            </div>
            <div>
              <input type="checkbox" id="badminton-equip6" />
              <label for="badminton-equip6" class="check-label">가방</label>
            </div>
          </div>
        </div>
      </div>
    </div>
    <hr style="margin: 0" />
  </div>
</template>

<script setup>
import { ref, defineEmits } from "vue";

const emit = defineEmits(["sort-change", "facility-change", "exercise-change"]);

const selectedExercise = ref("");

const onSortChange = (event) => {
  emit("sort-change", event.target.value);
};

const onFacilityChange = (event) => {
  emit("facility-change", event.target.value);
};

const onTypeChange = (event) => {
  selectedExercise.value = event.target.value;
  emit("exercise-change", event.target.value);
};
</script>

<style scoped>
.accordion-button {
  padding: 1rem 25px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
  font-size: 15px;
  height: 79px;
  font-family: "Courier New", Courier, monospace;
}

.accordion-button:focus {
  height: 79px;
  padding: 1rem 25px;
  z-index: 3;
  outline: 0;
  background-color: #fff;
  color: #8d8d8d;
  border-color: #fff;
  font-weight: bold;
  font-size: 15px;
  box-shadow: 0 0 0 0 rgb(255, 255, 255);
}

.accordion-button:not(.collapsed) {
  height: 80px;
  padding: 1rem 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  color: #000000;
  font-weight: bold;
  font-size: 15px;
  border-bottom: 1px solid #00000026;
}

.accordion-item {
  background-color: #fff;
  border: 0;
}

.accordion-body {
  padding: 0 1rem;
  height: 120px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.filter-option {
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
}

.check-label {
  margin-left: 8px;
  font-size: 13px;
}

.filter-option > div {
  width: 100px;
}

.option-text {
  color: #0000008a;
  display: flex;
  justify-content: center;
  font-weight: bold;
  font-size: 15px;
}
</style>
