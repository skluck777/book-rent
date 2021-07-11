<template>

<v-data-table
    :headers="headers"
    :items="dashboard"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'Dashboard',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "dashboardId", value: "dashboardId" },
            { text: "customerId", value: "customerId" },
            { text: "orderId", value: "orderId" },
            { text: "paymentId", value: "paymentId" },
            { text: "rentId", value: "rentId" },
            { text: "payStatus", value: "payStatus" },
            { text: "orderStatus", value: "orderStatus" },
            { text: "orderDate", value: "orderDate" },
            { text: "cancelDate", value: "cancelDate" },
            { text: "returnDate", value: "returnDate" },
            { text: "payDate", value: "payDate" },
            { text: "price", value: "price" },
            { text: "payCancelDate", value: "payCancelDate" },
        ],
        dashboard : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/dashboards')

      this.dashboard = temp.data._embedded.dashboards;

    },
    methods: {
    }
  }
</script>

