<template>
  <div class="master-container">
    <info-modal></info-modal>
    <div class="reserves-list-container">
      <div class="reserves-list-title">Lista de Reservaciones</div>
      <div class="filter">
        <span>Filtrar por:</span>
        <input type="text" class="author" placeholder="Autor" v-model="filter.fauthor">
        <input type="email" class="email" placeholder="Email" v-model="filter.femail">
        <input type="number" class="people" v-model="filter.fnum">
        <input type="text" placeholder="Pasaporte" v-model="filter.fdocType">
        <input type="text" class="document" placeholder="Documento" v-model="filter.fdoc">
        <select id="resKind" required v-model="filter.ftype">
          <option value="Cena">Cena</option>
          <option value="Almuerzo">Almuerzo</option>
          <option value="Once">Once</option>
          <option value="Cumpleaños">Cumpleaños</option>
          <option value="Especial">Especial</option>
        </select>
      </div>
      <ul class="reserves-list">
        <li class="reserve-item" v-for="(r, i) of freserves" :key="i" @click.stop="choseReserve(r)">
          <div class="info-div">
            <div class="upper-row">
              <div class="author"><b>{{ r.name }}</b></div>
              <div class="event-ty">{{ r.reserveType }}</div>
            </div>
            <div class="date">{{ formatDate(r.reserveDate) }}</div>
          </div>
          <div class="status-div">
            <div class="status" :class="{ confirmed: r.confirmed }">
              {{ r.confirmed?'Confirmada':'Sin confirmar' }}
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import InfoModal from '@/components/InfoModal.vue';
import { useStore } from 'vuex';

export default {
  name: 'Dashboard',
  components: {
    InfoModal
  },
  setup() {
    const router = useRouter();
    const store = useStore();
    const chosen_res = ref({});
    const filter = ref({
      fauthor: '',
      femail: '',
      fdoc: '',
      fdocType: '',
      ftype: '',
      fnum: 0
    })

    const reserves = computed(() => store.state.reserves);
    const freserves = computed(() => {
      const resv = reserves.value;
      const byAuthor = filter.value.fauthor?resv.filter((r) => r.name == filter.value.fauthor):resv
      const byEmail = filter.value.femail?byAuthor.filter((r) => r.email == filter.value.femail):byAuthor
      const byDocType = filter.value.fdocType?byEmail.filter((r) => r.docType == filter.value.fdocType):byEmail
      const byDoc = filter.value.fdoc?byDocType.filter((r) => r.docNumber == filter.value.fdoc):byDocType
      const byDate = filter.value.fdate?byDoc.filter((r) => r.reserveDate == filter.value.fdate):byDoc
      const byType = filter.value.ftype?byDate.filter((r) => r.reserveType == filter.value.ftype):byDate

      return byType;
    });
    const activeModal = ref(true);

    const formatDate = (date) => {
      const splitted = date.split('T');
      const hour = splitted[1].split(':');
      return splitted[0] + ' ' + hour[0] + ':' + hour[1];
    }

    const choseReserve = (res) => {
      store.commit('setChosenReserve', res);
      store.commit('switchInfoModal', true);
    }

    onMounted(async () => {
      const token = localStorage.getItem('jwt');
      const url = 'http://localhost:8080/reserve';
      const resp = await fetch(url, {
        method: 'get',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + token,
        }
      });
      
      if (resp.status == 200) {
        const json = await resp.json();
        store.commit('setReserves', json);
        return;
      }
      router.push('/login');
    });

    return { filter, freserves, activeModal, reserves, chosen_res, formatDate, choseReserve };
  }
}
</script>

<style scoped>
* {
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box;
  box-sizing:border-box;
}
.reserve-item {
  width: calc(100% - 10px);
  min-height: 40px;

  box-shadow: 0px 0px 2px 3px gray;
  border-radius: 5px;
  padding: 5px;
  margin-bottom: 10px;

  display: grid;
  grid-template-columns: calc(100% - 70px) 70px;
}

.reserve-item:hover {
  box-shadow: 0px 0px 3px 3px rgb(17, 85, 117);
}
.upper-row {
  width: 60%;
  display: flex;
  justify-content: space-between;
}

.reserve-item .date {
  font-size: 15px;
  font-style: italic;
}
.filter {
  width: 100%;
  height: 60px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 50% 50%;

  position: relative;
}

.filter span {
  font-size: 15px;
  font-weight: 600;
  font-style: italic;
  position: absolute;
  top: -17px;
  left: 5px;
}

.filter input, .filter select {
  width: 99%;
}

.filter button {
  width: 95%;
  justify-self: center;
  font-weight: 600;
}

.status-div {
  display: flex;
  justify-content: center;
  align-items: center;
}

.status {
  text-align: center;
  font-size: 14px;
  font-weight: 600;
}

.status.confirmed {
  color: green;
}



.master-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.reserves-list-container {
  width: 90%;
  max-width: 550px;
  height: 90%;

  padding: 10px;
  border: 2px solid rgb(21, 63, 119);
  border-radius: 10px;
}

.reserves-list-title {
  text-align: center;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 10px;
}

.reserves-list {
  max-height: calc(100% - 110px) !important;

  padding: 0px;
  padding-top: 10px;
  padding-left: 5px;
  padding-right: 5px;
  list-style: none;
  margin: 0px;
  margin-top: 10px;

  overflow-y: auto;
}

.reserves-list::-webkit-scrollbar {
  width: 10px;
}

.reserves-list::-webkit-scrollbar-track {
  width: 10px;
  background-color: rgb(207, 207, 207);
}

.reserves-list::-webkit-scrollbar-thumb {
  width: 10px;
  background-color: rgb(0, 71, 163);
  border-radius: 5px;
}


@media (max-width: 450px) {
  .filter {
    height: 80px;
    grid-template-columns: 50% 50%;
    grid-template-rows: 1fr 1fr 1fr;
  }

  .reserves-list {
    height: calc(100% - 130px);
  }
}
</style>
