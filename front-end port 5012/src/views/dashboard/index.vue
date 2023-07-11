<template>
  <div class="dashboard-container">
    <div class="dashboard-text">{{ name }}</div>
    <el-card>
      <el-button @click="resetDateFilter">Reset Date Filter</el-button>
      <el-button @click="clearFilter">Reset All Filters</el-button>
      <el-table ref="filterTable" :data="diaryList" style="width: 100%">
        <el-table-column prop="date" label="date" sortable width="150" column-key="date" />
        <el-table-column
          prop="weather"
          label="weather"
          width="150"
          :filters="[
            { text: 'Sunny', value: 'sunny' },
            { text: 'Cloud', value: 'cloud' },
            { text: 'Windy', value: 'windy' },
            { text: 'Rain', value: 'rain' },
            { text: 'Heavy Rain', value: 'heavy rain' },
            { text: 'Snow', value: 'snow' },
            { text: 'Heavy Snow', value: 'heavy snow' },
            { text: 'Lightning', value: 'lightning' },
            { text: 'Storm', value: 'storm' },
            { text: 'Other', value: 'other' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.weather === 'sunny' ? 'primary' : (scope.row.weather === 'cloud' || scope.row.weather === 'windy' ? 'success' : 'warning')"
              disable-transitions
            >{{ scope.row.weather }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="location" label="location" width="150" />
        <el-table-column prop="title" label="title" />
        <el-table-column prop="modify" label=" " width="85">
          <el-tag
            :type="'info'"
            disable-transitions
          >Modify</el-tag>
        </el-table-column>
        <el-table-column prop="modify" label=" " width="85">
          <el-tag
            :type="'danger'"
            disable-transitions
          ><a @click="toInfo">Delete</a></el-tag>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import diaryApi from '@/api/diary.js'
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  data() {
    return {
      diaryList: []
    }
  },
  computed: {
    ...mapGetters(['name'])
  },
  created() {
    this.getDiaryList()
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date')
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter()
    },
    formatter(row, column) {
      return row.address
    },
    filterTag(value, row) {
      return row.tag === value
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },
    getDiaryList() {
      diaryApi.getDiaryList().then(response => {
        this.diaryList = response.data
      })
    },
    toInfo() {
      this.loading = true
      this.$store.dispatch('dashboard', this.loginForm).then(() => {
        console.log('redirect', this.redirect)
        this.$router.push({ path: '/info' + 1 })
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 20px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
