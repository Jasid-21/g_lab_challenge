<template>
  <form class="info-container" :class="{ active }" ref="element" @submit.prevent="updateReserve">
    <div class="status" :class="{ confirmed: reserve.confirmed }">
      {{ reserve.confirmed?'Confirmada':'Sin confirmar' }}
    </div>
    <label for="author">Autor: </label>
    <input type="text" id="author" v-model="reserve.name" required>
    <br>

    <label for="email">Email: </label>
    <input type="email" id="email" v-model="reserve.email" required>
    <br>

    <label for="docType">Tipo documento: </label>
    <input type="text" id="docType" v-model="reserve.docType" required>
    <br>
    
    <label for="docNum">Númeor documento: </label>
    <input type="text" id="docNum" v-model="reserve.docNumber" required>
    <br>
    
    <label for="date">Fecha reservación: </label>
    <input type="datetime-local" id="date" v-model="reserve.reserveDate" required>
    <br>
    
    <label for="resKind">Tipo reservación: </label>
    <select id="resKind" v-model="reserve.reserveType" required>
      <option value="Cena">Cena</option>
      <option value="Almuerzo">Almuerzo</option>
      <option value="Once">Once</option>
      <option value="Cumpleaños">Cumpleaños</option>
      <option value="Especial">Especial</option>
    </select>
    <br>
    
    <label for="people">Numero personas: </label>
    <input type="number" id="people" v-model="reserve.people" required>
    <br>
    
    <label for="desc">Observaciones: </label>
    <textarea id="desc" cols="30" rows="10" v-model="reserve.observations"></textarea>
    <br>

    <div class="buttons-div">
      <button class="edit" type="submit">Editar</button>
      <button class="confirm" @click.prevent="confirmReserve" :disabled="reserve.confirmed">
        Confirmar
      </button>
    </div>
  </form>
</template>

<script>
import { computed, onBeforeUnmount, onMounted, ref } from 'vue'
import { useStore } from 'vuex';

export default {
  name: 'InfoModal',
  setup() {
    const element = ref(null);
    const store = useStore();
    const active = computed(() => store.state.infoModal);
    const reserve = computed(() => store.state.chosenReserve);

    const handleClick = (event) => {
      event.stopPropagation();
      if (!element.value.contains(event.target) && event.target.classList[0] != 'reserve-item') {
        store.commit('switchInfoModal', false);
      }
    }

    const updateReserve = async () => {
      if (!reserve.value) return;
      const token = localStorage.getItem('jwt');
      const url = 'http://localhost:8080/reserve/update';
      const resp = await fetch(url, {
        method: 'put',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + token,
        },
        body: JSON.stringify(reserve.value),
      });
      
      if (resp.ok) {
        store.dispatch('updateReserves', reserve.value);
        alert('Reservación actualizada con éxito');
        return;
      }
    }

    const confirmReserve = () => {
      if (!reserve.value) return;
      if(!confirm("Estás seguro que deseas confirmar esta reservación?")) return;
      store.commit('confirmReserve');

      updateReserve();
    }

    onMounted(() => {
      document.addEventListener('click', handleClick);
    });

    onBeforeUnmount(() => {
      document.removeEventListener('click', handleClick);
    });

    return { element, active, reserve, updateReserve, confirmReserve };
  }
}
</script>

<style scoped>
.status {
  font-style: italic;
  font-weight: 600;
  position: absolute;
  right: 10px;
  top: 10px;
}

.status.confirmed {
  color: green;
}
.info-container {
  z-index: 30;
  position: relative;
  width: 300px;
  height: 530px;

  background-color: white;
  box-shadow: 0px 0px 3px 2px gray;
  border-radius: 10px;
  padding: 10px;

  position: absolute;
  top: 50%;
  left: -500px;
  translate: 0px -50%;

  transition-property: left;
  transition-duration: 180ms;

  font-size: 14px;
}

.info-container input, select, textarea {
  width: calc(100% - 10px);
  height: 23px;
  margin-bottom: 10px;
}

.info-container textarea {
  height: 80px;
}

.buttons-div {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

button {
  width: 70px;
  height: 25px;
}

.info-container.active {
  left: 10px;
}
</style>