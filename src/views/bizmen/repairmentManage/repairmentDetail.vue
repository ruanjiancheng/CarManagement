<template>
  <div>
    <el-card>
      维修项目：{{ this.name }}
<!--      TODO-->
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            fixed
            prop="name"
            label="材料名称"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="price"
            label="材料价格"
            width="150">
        </el-table-column>
        <el-table-column
            fixed
            prop="count"
            label="数量"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作"
            width="150">
          <template slot-scope="scope">
            <el-popconfirm
                confirm-button-text='删除'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="删除不可复原"
                @confirm="deleteTeacher(scope.row)"
            >
              <el-button slot="reference" type="text" size="small">删除</el-button>
            </el-popconfirm>
          </template>
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
      金额：{{ totalPrice }}
    </el-card>
  </div>
</template>

<script>
export default {
  name: "repairmentList",
  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      id: null,
      name: null,
      tmpList: null,
      totalPrice: 0,
      type: sessionStorage.getItem("type"),
    }
  },
  created: function() {
    const that = this
    that.tmpList = null
    that.total = null
    that.tableData = null
    that.id = this.$route.query.id
    that.name = this.$route.query.name
    // TODO
    const url2 = this.$url + '/api/repair/queryMaterialByRepairID?repairmentID='
    axios.get(url2 + that.id).then(function (resp) {
      console.log('data: ', resp.data)
      that.tmpList = resp.data.data
      let tt = []
      for (let i = 0; i < that.tmpList.length; i++) {
        if (that.tmpList[i].count !== 0) {
          tt.push(that.tmpList[i])
          that.totalPrice += that.tmpList[i].count * that.tmpList[i].price
        }
      }
      that.tmpList = tt
      console.log(that.tmpList)
      that.total = that.tmpList.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    })
  },
  methods: {
    select(row) {
      console.log(row)
    },
    deleteTeacher(row) {
      const that = this
      const url = this.$url + "/api/repair/insertMaterial"
      this.ruleForm = {materialID: row.materialID, repairmentID: parseInt(that.id), count: -row.count}
      console.log(this.ruleForm)
      axios.post(url, this.ruleForm).then(function (resp) {
        console.log(resp)
        if (resp.data.code === 1000) {
          that.$message({
            showClose: true,
            message: '插入成功',
            type: 'success'
          });
        }
        else {
          that.$message.error('插入失败，请检查数据库');
        }
        that.$router.push("/repairmentList")
      })
    },
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
    editor(row) {
      this.$router.push({
        path: '/repairmentAddMaterial',
        query: {
          id: row.id,
          name: row.name
        }
      })
    },
    list(row) {
      this.$router.push({
        path: '/repairmentDetail',
        query: {
          id: row.id
        }
      })
    },
    add() {
      this.$router.push({path: '/addRepairment'})
    }
  }
}
</script>

<style scoped>

</style>