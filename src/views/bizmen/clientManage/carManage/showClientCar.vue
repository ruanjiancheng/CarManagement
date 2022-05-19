<template>
  <div>
    <el-card>
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            fixed
            prop="numberPlate"
            label="车牌号"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="vin"
            label="车架号"
            width="200">
        </el-table-column>
        <el-table-column
            prop="color"
            label="颜色"
            width="150">
        </el-table-column>
        <el-table-column
            prop="model"
            label="厂商"
            width="150">
        </el-table-column>
        <el-table-column
            prop="category"
            label="型号"
            width="150">
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="changePage"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "showClientCar",
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      type: sessionStorage.getItem("type"),
      id: '4399KK'
    }
  },
  created: function() {
    const that = this
    if (this.$route.query.id !== undefined) {
      this.id = this.$route.query.id
    }
    that.tmpList = null
    that.total = null
    that.tableData = null
    const url = this.$url + '/api/client/getClientCars?clientID='
    console.log(url + this.id)
    axios.get(url + this.id).then(function (resp) {
      console.log(resp.data.data)
      that.tmpList = resp.data.data
      for (let i = 0; i < that.tmpList.length; i++) {
        that.tmpList[i].workerType = that.$workerType[that.tmpList[i].workerType]
      }
      console.log(that.tmpList)
      that.total = resp.data.data.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    })
  },
  methods: {
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
  }
}
</script>

<style scoped>

</style>