<template>
  <div class="master-container">
    <div class="form-container">
      <h3>Solicitar Reservación</h3>
      <form class="info-container" ref="element" @submit.prevent="requestReserve">
        <div class="form-row">
          <div class="row-item">
            <label for="author">Autor: </label>
            <input type="text" id="author" v-model="reserve.name" required>
          </div>

          <div class="row-item">
            <label for="email">Email: </label>
            <input type="email" id="email" v-model="reserve.email" required>
          </div>
        </div>

        <div class="form-row">
          <div class="row-item">
            <label for="docType">Tipo documento: </label>
            <input type="text" id="docType" v-model="reserve.docType" required>
          </div>

          <div class="row-item">
            <label for="docNum">Número documento: </label>
            <input type="text" id="docNum" v-model="reserve.docNumber" required>
          </div>
        </div>
        
        <div class="form-row-three">
          <div class="row-item">
            <label for="date">Fecha reservación: </label>
            <input type="datetime-local" id="date" v-model="reserve.reserveDate" required>
          </div>

          <div class="row-item">
            <label for="resKind">Tipo reservación: </label>
            <select id="resKind" v-model="reserve.reserveType" required>
              <option value="Cena">Cena</option>
              <option value="Almuerzo">Almuerzo</option>
              <option value="Once">Once</option>
              <option value="Cumpleaños">Cumpleaños</option>
              <option value="Especial">Especial</option>
            </select>
          </div>

          <div class="row-item">
            <label for="people">Numero personas: </label>
            <input type="number" id="people" v-model="reserve.people" required>
          </div>
        </div>

        <label for="desc">Observaciones: </label>
        <textarea id="desc" cols="30" rows="10" v-model="reserve.observations"></textarea>

        <div class="buttons-div">
          <button class="edit" type="submit">Solicitar</button>
          <button class="confirm" @click.prevent="refreshForm">
            Limpiar
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  name: 'ReserveView',
  setup() {
    const reserve = ref({
      name: 'a name',
      email: 'alguien@gmail.com',
      docType: 'Pasaporte',
      docNumber: '1234567poiuy',
      reserveDate: '',
      reserveType: 'Birthday',
      people: 15,
      observations: ''
    });

    const requestReserve = async () => {
      const url = 'http://localhost:8080/reserve'
      const resp = await fetch(url, {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(reserve.value),
      });

      if (resp.ok) {
        alert("Solicitud realizada con éxito");
        return;
      }
    }

    const refreshForm = () => {

    }

    return { reserve, requestReserve, refreshForm };
  }
}
</script>

<style scoped>
.form-row {
  width: 100%;
  display: grid;
  grid-template-columns: 50% 50%;
  grid-template-rows: 1fr;
}

.form-row-three {
  display: grid;
  grid-template-columns: 33% 33% 33%;
  grid-template-rows: 1fr;
}

.row-item {
  width: 98%;
}

.master-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
.form-container {
  width: 90%;
  max-width: 600px;
  min-width: 300px;
  height: 530px;

  box-shadow: 0px 0px 3px 2px gray;
  background-color: white;

  border-radius: 10px;
  padding: 10px;

  overflow-y: auto;
}

.form-container::-webkit-scrollbar {
  width: 10px;
}

.form-container::-webkit-scrollbar-track {
  width: 10px;
  background-color: rgb(207, 207, 207);
}

.form-container::-webkit-scrollbar-thumb {
  width: 10px;
  background-color: rgb(0, 71, 163);
  border-radius: 5px;
}

.form-container h3 {
  text-align: center;
}
.info-container {
  font-size: 14px;
}

.info-container input, select, textarea {
  width: calc(100% - 10px);
  height: 23px;
  margin-bottom: 10px;
}

.info-container select {
  height: 29px;
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

@media (max-width: 430px) {
  .form-row, .form-row-three {
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-wrap: wrap;
  }
}

</style>